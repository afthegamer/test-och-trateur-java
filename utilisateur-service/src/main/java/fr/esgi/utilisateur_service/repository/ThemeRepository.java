package fr.esgi.utilisateur_service.repository;

import fr.esgi.utilisateur_service.business.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByNom(String nom);
}
