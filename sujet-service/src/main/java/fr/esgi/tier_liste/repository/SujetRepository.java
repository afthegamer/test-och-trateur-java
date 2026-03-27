package fr.esgi.tier_liste.repository;

import fr.esgi.tier_liste.business.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SujetRepository extends JpaRepository<Sujet, Long> {

    // Requête par dérivation
    List<Sujet> findByElementsEmpty();

    @Query("""
            SELECT s
            FROM Sujet s
            WHERE s.elements is empty
            """)
    List<Sujet> findByElementsEmptyHQL();

}