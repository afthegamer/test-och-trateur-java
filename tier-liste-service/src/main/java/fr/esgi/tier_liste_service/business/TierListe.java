package fr.esgi.tier_liste_service.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * En microservices, les références cross-service sont remplacées par des IDs simples.
 * utilisateurId → utilisateur-service (port 8082)
 * elementId     → sujet-service      (port 8480)
 * categorieId   → categorie-service  (port 8083)
 */
@Entity
@Data
@NoArgsConstructor
public class TierListe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'identifiant de l'utilisateur est obligatoire")
    private Long utilisateurId;

    @NotNull(message = "L'identifiant de l'élément est obligatoire")
    private Long elementId;

    @NotNull(message = "L'identifiant de la catégorie est obligatoire")
    private Long categorieId;

    @CreationTimestamp
    private LocalDateTime dateHeurePlacement;

    @Version
    private Integer version;
}
