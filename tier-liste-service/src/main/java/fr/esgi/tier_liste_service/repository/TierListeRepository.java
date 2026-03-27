package fr.esgi.tier_liste_service.repository;

import fr.esgi.tier_liste_service.business.TierListe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TierListeRepository extends JpaRepository<TierListe, Long> {

    List<TierListe> findByUtilisateurId(Long utilisateurId);

    List<TierListe> findByUtilisateurIdAndCategorieId(Long utilisateurId, Long categorieId);

    List<TierListe> findByElementId(Long elementId);

    boolean existsByUtilisateurId(Long utilisateurId);
}
