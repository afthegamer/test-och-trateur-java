package fr.esgi.tier_liste.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link fr.esgi.tier_liste.business.Sujet}
 */
public record SujetDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @NotBlank(message="Merci de renseigner le nom du sujet") String nom,
        String description) implements Serializable {
}