package fr.esgi.tier_liste_service.service;

import fr.esgi.tier_liste_service.dto.TierListeDto;

import java.util.List;
import java.util.Optional;

public interface TierListeService {

    TierListeDto ajouterTierListe(TierListeDto dto);

    Optional<TierListeDto> recupererParId(Long id);

    List<TierListeDto> recupererToutes();

    List<TierListeDto> recupererParUtilisateur(Long utilisateurId);

    List<TierListeDto> recupererParUtilisateurEtCategorie(Long utilisateurId, Long categorieId);

    Optional<TierListeDto> modifierCategorie(Long id, Long categorieId);

    void supprimerTierListe(Long id);
}
