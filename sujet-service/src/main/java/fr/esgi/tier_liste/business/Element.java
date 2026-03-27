package fr.esgi.tier_liste.business;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // Hibernate va créer une colonne sujet_id avec une contrainte
    // foreign key
    @ManyToOne
    @ToString.Exclude
    private Sujet sujet;

    public Element(String nom, Sujet sujet) {
        this.nom = nom;
        this.sujet = sujet;
    }
}
