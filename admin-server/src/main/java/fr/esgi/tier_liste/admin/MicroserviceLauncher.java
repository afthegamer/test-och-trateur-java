package fr.esgi.tier_liste.admin;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ConfigurationProperties(prefix = "launcher")
public class MicroserviceLauncher {

    private static final Logger log = LoggerFactory.getLogger(MicroserviceLauncher.class);
    private Path baseDir;

    private List<ServiceConfig> services = new ArrayList<>();
    private final List<Process> runningProcesses = new CopyOnWriteArrayList<>();
    private final List<Process> buildProcesses = new CopyOnWriteArrayList<>();

    public List<ServiceConfig> getServices() {
        return services;
    }

    public void setServices(List<ServiceConfig> services) {
        this.services = services;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void launchAll() {
        if (services.isEmpty()) {
            log.info("Aucun service configure dans launcher.services");
            return;
        }

        // Résoudre le répertoire de base à partir du classpath (là où se trouve le JAR/classes)
        try {
            Path classLocation = Path.of(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            // Si c'est un JAR dans target/, remonter à la racine du projet
            if (classLocation.toString().endsWith(".jar")) {
                baseDir = classLocation.getParent().getParent();
            } else {
                // En mode IDE, classes sont dans target/classes/
                baseDir = classLocation.getParent().getParent();
            }
        } catch (Exception e) {
            baseDir = Path.of(".").toAbsolutePath();
            log.warn("Impossible de determiner le repertoire de base, utilisation de {}", baseDir);
        }

        log.info("=== Demarrage de {} microservice(s) en parallele (base: {}) ===", services.size(), baseDir);

        // Lancement parallèle de tous les services
        for (ServiceConfig service : services) {
            Thread.startVirtualThread(() -> {
                try {
                    launch(service);
                    waitForService(service);
                    log.info("[{}] PRET sur le port {}", service.getName(), service.getPort());
                } catch (Exception e) {
                    log.error("[{}] Echec du demarrage", service.getName(), e);
                }
            });
        }
    }

    private void launch(ServiceConfig service) throws Exception {
        File workDir = baseDir.resolve(service.getPath()).toFile().getCanonicalFile();
        if (!workDir.exists() || !workDir.isDirectory()) {
            throw new IllegalStateException("Repertoire introuvable: " + workDir);
        }

        // Validation : le répertoire doit contenir un pom.xml (projet Maven valide)
        if (!new File(workDir, "pom.xml").exists()) {
            throw new IllegalStateException("Pas de pom.xml dans " + workDir + " — ce n'est pas un projet Maven valide");
        }

        // Validation du port
        if (service.getPort() < 1 || service.getPort() > 65535) {
            throw new IllegalStateException("Port invalide pour " + service.getName() + ": " + service.getPort());
        }

        // Chercher un JAR pré-compilé dans target/
        Path jar = findJar(workDir.toPath());

        if (jar == null) {
            log.info("[{}] Pas de JAR trouve, build en cours...", service.getName());
            buildService(workDir);
            jar = findJar(workDir.toPath());
            if (jar == null) {
                throw new IllegalStateException("Build termine mais aucun JAR dans " + workDir + "/target/");
            }
        }

        log.info("[{}] Lancement de {}...", service.getName(), jar.getFileName());

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", jar.toString());
        pb.directory(workDir);
        pb.redirectErrorStream(true);
        pb.inheritIO();

        Process process = pb.start();
        runningProcesses.add(process);
    }

    private Path findJar(Path projectDir) throws IOException {
        Path targetDir = projectDir.resolve("target");
        if (!Files.exists(targetDir)) {
            return null;
        }
        try (var stream = Files.list(targetDir)) {
            return stream
                    .filter(p -> p.toString().endsWith(".jar"))
                    .filter(p -> !p.toString().endsWith("-sources.jar"))
                    .filter(p -> !p.toString().endsWith("-javadoc.jar"))
                    .filter(p -> !p.toString().endsWith(".jar.original"))
                    .findFirst()
                    .orElse(null);
        }
    }

    private void buildService(File workDir) throws Exception {
        String mvnw = System.getProperty("os.name").toLowerCase().contains("win")
                ? "mvnw.cmd"
                : "./mvnw";

        ProcessBuilder pb = new ProcessBuilder(mvnw, "package", "-DskipTests", "-q");
        pb.directory(workDir);
        pb.redirectErrorStream(true);
        pb.inheritIO();

        Process build = pb.start();
        buildProcesses.add(build);
        int exitCode = build.waitFor();
        buildProcesses.remove(build);
        if (exitCode != 0) {
            throw new IllegalStateException("Build echoue avec le code " + exitCode);
        }
    }

    private void waitForService(ServiceConfig service) throws InterruptedException {
        String url = "http://localhost:" + service.getPort();
        int maxAttempts = service.getTimeoutSeconds() / 2;

        for (int i = 0; i < maxAttempts; i++) {
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(1000);
                conn.getResponseCode();
                return;
            } catch (Exception e) {
                Thread.sleep(2000);
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
        log.warn("[{}] Pas de reponse apres {}s", service.getName(), service.getTimeoutSeconds());
    }

    @PreDestroy
    public void shutdownAll() {
        log.info("=== Arret des microservices ===");

        // Tuer les builds Maven en cours
        for (Process build : buildProcesses) {
            if (build.isAlive()) {
                build.destroyForcibly();
            }
        }

        for (Process process : runningProcesses.reversed()) {
            if (process.isAlive()) {
                // Détruire les enfants d'abord, puis le parent
                process.descendants().forEach(ph -> {
                    ph.destroy();
                    try { ph.onExit().get(5, java.util.concurrent.TimeUnit.SECONDS); } catch (Exception ignored) {}
                });
                process.destroy();
                try { process.onExit().get(10, java.util.concurrent.TimeUnit.SECONDS); } catch (Exception ignored) {}
                if (process.isAlive()) {
                    log.warn("Process toujours actif, force kill");
                    process.destroyForcibly();
                }
            }
        }
        log.info("=== Tous les services sont arretes ===");
    }

    public static class ServiceConfig {
        private String name;
        private String path;
        private int port;
        private int timeoutSeconds = 120;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
        public int getTimeoutSeconds() { return timeoutSeconds; }
        public void setTimeoutSeconds(int timeoutSeconds) { this.timeoutSeconds = timeoutSeconds; }
    }
}
