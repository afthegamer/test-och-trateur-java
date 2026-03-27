package fr.esgi.categorie_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record CategorieDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @NotBlank(message = "Merci de renseigner le nom de la catégorie") String nom,
        Integer rang
) implements Serializable {
}
