package fr.esgi.categorie_service.repository;

import fr.esgi.categorie_service.business.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    List<Categorie> findDistinctByNomIgnoreCaseOrderByIdDesc(String nom);

    List<Categorie> findTop5DistinctByNomIgnoreCaseOrderByIdDesc(String nom);

    long deleteByIdGreaterThan(Long id);
}
