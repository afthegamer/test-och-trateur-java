package fr.esgi.colonne_service.service;

import fr.esgi.colonne_service.dto.ColonneDto;

import java.util.List;

public interface ColonneService {

    ColonneDto ajouterColonne(ColonneDto dto);

    ColonneDto recupererColonne(Long id);

    List<ColonneDto> recupererColonnes();

    ColonneDto patcherColonne(Long id, ColonneDto dto);

    ColonneDto mettreAJourColonne(Long id, ColonneDto dto);

    void supprimerColonne(Long id);
}
