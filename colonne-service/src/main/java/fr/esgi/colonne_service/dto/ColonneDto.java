package fr.esgi.colonne_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ColonneDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @NotBlank(message = "Merci de renseigner le nom de la colonne") String nom,
        Integer position
) implements Serializable {
}
