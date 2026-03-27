package fr.esgi.colonne_service.repository;

import fr.esgi.colonne_service.business.Colonne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColonneRepository extends JpaRepository<Colonne, Long> {
}
