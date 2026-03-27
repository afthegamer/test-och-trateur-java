package fr.esgi.tier_liste.service;

import fr.esgi.tier_liste.business.Sujet;
import fr.esgi.tier_liste.dto.SujetDto;

import java.util.List;
import java.util.Map;

public interface SujetService {

    SujetDto ajouterSujet(SujetDto sujetDto);

    SujetDto recupererSujet(Long id);

    List<SujetDto> recupererSujets();

    SujetDto patcherSujet(Long id, SujetDto sujetDto);

    void supprimerSujet(Long id);

    SujetDto mettreAJourSujet(Long id, SujetDto sujetDto);
}
