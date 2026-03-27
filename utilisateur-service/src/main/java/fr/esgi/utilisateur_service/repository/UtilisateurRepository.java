package fr.esgi.utilisateur_service.repository;

import fr.esgi.utilisateur_service.business.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    boolean existsByEmail(String email);

    Optional<Utilisateur> findByEmail(String email);

    @Query("""
            FROM Utilisateur u
            WHERE u.prenom LIKE 'A%'
            """)
    List<Utilisateur> findUtilisateursAyantPrenomDebutantParA();

    List<Utilisateur> findByPrenomStartsWithIgnoreCase(String prenom);

    List<Utilisateur> findByDateDeNaissanceBefore(LocalDate dateDeNaissance);

    @Query("""
            FROM Utilisateur
            WHERE month(dateDeNaissance) = month(current_date())
              AND day(dateDeNaissance) = day(current_date())
            """)
    List<Utilisateur> findUtilisateursCelebrantLeurAnniversaireAujourdhui();

    @Query("""
            SELECT u FROM Utilisateur u
            WHERE u.dateDeNaissance <= current_date() - 30 year
              AND u.dateDeNaissance > current_date() - 40 year
            """)
    List<Utilisateur> findUtilisateursTrentenaires();

    List<Utilisateur> findByPrenomStartsWithIgnoreCaseAndDateDeNaissanceBetween(
            String prenom, LocalDate dateDeNaissanceStart, LocalDate dateDeNaissanceEnd);
}
