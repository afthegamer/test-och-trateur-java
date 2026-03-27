package fr.esgi.categorie_service.service;

import fr.esgi.categorie_service.dto.CategorieDto;

import java.util.List;

public interface CategorieService {

    CategorieDto ajouterCategorie(CategorieDto dto);

    CategorieDto recupererCategorie(Long id);

    List<CategorieDto> recupererCategories();

    CategorieDto patcherCategorie(Long id, CategorieDto dto);

    CategorieDto mettreAJourCategorie(Long id, CategorieDto dto);

    void supprimerCategorie(Long id);
}
