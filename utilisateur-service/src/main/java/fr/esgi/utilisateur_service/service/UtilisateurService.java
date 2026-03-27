package fr.esgi.utilisateur_service.service;

import fr.esgi.utilisateur_service.business.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);

    long compterUtilisateurs();

    List<Utilisateur> recupererTous();

    Optional<Utilisateur> recupererParId(Long id);

    Optional<Utilisateur> modifierUtilisateur(Long id, Utilisateur utilisateur);

    boolean supprimerUtilisateur(Long id);

    List<Utilisateur> recupererUtilisateursCelebrantLeurAnniversaire();
}
