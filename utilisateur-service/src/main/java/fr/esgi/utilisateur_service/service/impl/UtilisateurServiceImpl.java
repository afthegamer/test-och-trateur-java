package fr.esgi.utilisateur_service.service.impl;

import fr.esgi.utilisateur_service.business.Utilisateur;
import fr.esgi.utilisateur_service.exception.EmailDejaPresentException;
import fr.esgi.utilisateur_service.repository.UtilisateurRepository;
import fr.esgi.utilisateur_service.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new EmailDejaPresentException("Cet email est déjà présent en base");
        }
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public long compterUtilisateurs() {
        return utilisateurRepository.count();
    }

    @Override
    public List<Utilisateur> recupererTous() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Optional<Utilisateur> recupererParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public Optional<Utilisateur> modifierUtilisateur(Long id, Utilisateur utilisateur) {
        return utilisateurRepository.findById(id)
                .map(existing -> {
                    existing.setPrenom(utilisateur.getPrenom());
                    existing.setNom(utilisateur.getNom());
                    existing.setEmail(utilisateur.getEmail());
                    existing.setDateDeNaissance(utilisateur.getDateDeNaissance());
                    if (utilisateur.getTheme() != null) {
                        existing.setTheme(utilisateur.getTheme());
                    }
                    return utilisateurRepository.save(existing);
                });
    }

    @Override
    public boolean supprimerUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            return false;
        }
        utilisateurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Utilisateur> recupererUtilisateursCelebrantLeurAnniversaire() {
        return utilisateurRepository.findUtilisateursCelebrantLeurAnniversaireAujourdhui();
    }
}
