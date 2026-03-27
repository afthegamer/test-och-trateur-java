package fr.esgi.tier_liste.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Sujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    private String description;

    @OneToMany(mappedBy="sujet", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.JOIN)
    private List<Element> elements;

    public Sujet(String nom) {
        this.nom = nom;
    }
}
