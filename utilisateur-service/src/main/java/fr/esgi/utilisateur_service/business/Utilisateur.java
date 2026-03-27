package fr.esgi.utilisateur_service.business;

import fr.esgi.utilisateur_service.annotation.DixHuitAnsOuPlus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorColumn(name = "typeUtilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Merci de préciser votre prénom")
    private String prenom;

    @NotBlank(message = "Merci de préciser votre nom")
    private String nom;

    @Column(unique = true)
    @NotBlank(message = "Merci de renseigner votre adresse email")
    @Email(regexp = "^[a-zA-Z0-9._+-]+@skolae\\.fr$", message = "L'email doit être au format {regexp}")
    private String email;

    @Size(min = 8, message = "Votre mot de passe doit contenir au moins {min} caractères")
    @NotBlank(message = "Merci de renseigner un mot de passe")
    private String password;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Merci de renseigner votre date de naissance")
    @Past(message = "Merci de saisir une date dans le passé")
    @DixHuitAnsOuPlus(message = "Vous devez être majeur pour vous inscrire")
    private LocalDate dateDeNaissance;

    @ManyToOne
    @NotNull(message = "Veuillez choisir un thème")
    private Theme theme;

    @CreationTimestamp
    private LocalDateTime dateHeureInscription;

    @UpdateTimestamp
    private LocalDateTime dateHeureModification;

    @ManyToMany(mappedBy = "utilisateurs")
    private List<Interet> interets;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @PrePersist
    public void prePersist() {
        System.out.println("Invocation de la méthode prePersist pour " + email);
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Invocation de la méthode preRemove pour l'utilisateur ayant l'id " + id);
    }
}
