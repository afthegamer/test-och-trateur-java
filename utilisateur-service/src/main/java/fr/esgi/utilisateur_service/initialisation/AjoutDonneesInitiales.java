package fr.esgi.utilisateur_service.initialisation;

import fr.esgi.utilisateur_service.business.Administrateur;
import fr.esgi.utilisateur_service.business.Theme;
import fr.esgi.utilisateur_service.business.Utilisateur;
import fr.esgi.utilisateur_service.repository.AdministrateurRepository;
import fr.esgi.utilisateur_service.repository.ThemeRepository;
import fr.esgi.utilisateur_service.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales {

    private final ThemeRepository themeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AdministrateurRepository administrateurRepository;

    private static final Faker faker = new Faker(Locale.FRANCE);

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        ajouterThemes();
        ajouterUtilisateurs(100);
        ajouterAdministrateur();
    }

    private void ajouterThemes() {
        List.of("Light", "Dark").forEach(nom -> themeRepository.save(new Theme(nom)));
    }

    private void ajouterUtilisateurs(int nb) {
        Map<String, Utilisateur> utilisateurs = new HashMap<>();
        Theme theme = themeRepository.findByNom("Light");

        while (utilisateurs.size() < nb) {
            String nom = faker.name().lastName();
            String emailNom = nom.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            Utilisateur utilisateur = Utilisateur.builder()
                    .nom(nom)
                    .prenom(faker.name().firstName())
                    .email(emailNom + "@skolae.fr")
                    .password(faker.internet().password(8, 16))
                    .dateDeNaissance(faker.timeAndDate().birthday(18, 80))
                    .theme(theme)
                    .build();
            utilisateurs.put(emailNom, utilisateur);
        }

        String prenom = "Birthday";
        utilisateurs.put(prenom, Utilisateur.builder()
                .nom(prenom)
                .prenom(prenom)
                .email(prenom.toLowerCase() + "@skolae.fr")
                .password(faker.internet().password(8, 16))
                .dateDeNaissance(LocalDate.now().minusYears(20))
                .theme(themeRepository.findByNom("Dark"))
                .build());

        utilisateurRepository.saveAll(utilisateurs.values());
    }

    private void ajouterAdministrateur() {
        administrateurRepository.save(Administrateur.builder()
                .nom("CREPET")
                .prenom("Bénédicte")
                .email("benedicte@skolae.fr")
                .password("12345678")
                .dateDeNaissance(LocalDate.of(2000, 1, 1))
                .theme(themeRepository.findByNom("Light"))
                .numeroDeTelephone(faker.phoneNumber().phoneNumber())
                .build());
    }
}
