package fr.esgi.tier_liste.initialisation;

import fr.esgi.tier_liste.business.*;
import fr.esgi.tier_liste.repository.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

// Avec la décoration @Component, Spring instancie
// la classe d'ajout et place cette instance dans
// son conteneur IoC
@Component
@AllArgsConstructor
public class AjoutDonneesInitiales {

    private SujetRepository sujetRepository;
    private ElementRepository elementRepository;

    private EntityManager entityManager;

    // On déclare le faker en static pour ne pas que Spring
    // essaie de l'instancier
    private static Faker faker = new Faker(Locale.FRANCE);

    // Dès que l'évenement ApplicationReadyEvent est
    // écouté, la méthode init est invoquée par Spring
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        ajouterSujet();
        ajouterElements();
        Sujet sujet = sujetRepository.findById(1L).get();
        System.out.println(sujet.getElements());
    }

    private void ajouterSujet() {
        sujetRepository.save(new Sujet("Vêtements"));
    }

    private void ajouterElements() {
        Sujet sujet = sujetRepository.findAll().get(0);
        elementRepository.save(new Element("Jack & Jones", sujet));
        elementRepository.save(new Element("Jules", sujet));
        elementRepository.save(new Element("Devred", sujet));
        elementRepository.save(new Element("Eden Park", sujet));
        elementRepository.save(new Element("Celio", sujet));
        elementRepository.save(new Element("La Halle", sujet));
    }

}
