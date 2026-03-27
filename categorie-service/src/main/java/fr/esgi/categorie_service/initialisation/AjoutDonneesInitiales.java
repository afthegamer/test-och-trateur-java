package fr.esgi.categorie_service.initialisation;

import fr.esgi.categorie_service.business.Categorie;
import fr.esgi.categorie_service.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales {

    private final CategorieRepository categorieRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        List.of("S", "A", "B", "C", "D").forEach(nom -> categorieRepository.save(new Categorie(nom)));
    }
}
