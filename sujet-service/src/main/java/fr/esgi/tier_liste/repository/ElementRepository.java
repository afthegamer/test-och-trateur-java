package fr.esgi.tier_liste.repository;

import fr.esgi.tier_liste.business.Element;
import fr.esgi.tier_liste.business.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ElementRepository extends JpaRepository<Element, Long> {
    List<Element> findBySujet(Sujet sujet);

    // Dérivation
    List<Element> findBySujetId(Long id);

    // HQL
    @Query("""
            FROM Element
                        WHERE sujet.id=:idSujet
            """)
    List<Element> findBySujetIdHQL(@Param("idSujet") Long id);
}