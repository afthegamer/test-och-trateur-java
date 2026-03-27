package fr.esgi.utilisateur_service.repository;

import fr.esgi.utilisateur_service.business.Interet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteretRepository extends JpaRepository<Interet, Long> {
}
