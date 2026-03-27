package fr.esgi.tier_liste_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TierListeDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,

        @NotNull(message = "L'identifiant de l'utilisateur est obligatoire")
        Long utilisateurId,

        @NotNull(message = "L'identifiant de l'élément est obligatoire")
        Long elementId,

        @NotNull(message = "L'identifiant de la catégorie est obligatoire")
        Long categorieId,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime dateHeurePlacement
) implements Serializable {
}
