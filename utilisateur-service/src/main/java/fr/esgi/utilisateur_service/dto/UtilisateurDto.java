package fr.esgi.utilisateur_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.utilisateur_service.annotation.DixHuitAnsOuPlus;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

public record UtilisateurDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,

        @NotBlank(message = "Merci de préciser votre prénom")
        String prenom,

        @NotBlank(message = "Merci de préciser votre nom")
        String nom,

        @NotBlank(message = "Merci de renseigner votre adresse email")
        @Email(regexp = "^[a-zA-Z0-9._+-]+@skolae\\.fr$", message = "L'email doit être au format {regexp}")
        String email,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Size(min = 8, message = "Votre mot de passe doit contenir au moins {min} caractères")
        @NotBlank(message = "Merci de renseigner un mot de passe")
        String password,

        @NotNull(message = "Merci de renseigner votre date de naissance")
        @Past(message = "Merci de saisir une date dans le passé")
        @DixHuitAnsOuPlus(message = "Vous devez être majeur pour vous inscrire")
        LocalDate dateDeNaissance,

        Long themeId
) implements Serializable {
}
