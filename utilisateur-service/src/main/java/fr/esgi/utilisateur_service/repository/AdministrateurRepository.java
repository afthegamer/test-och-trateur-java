package fr.esgi.utilisateur_service.repository;

import fr.esgi.utilisateur_service.business.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
}
