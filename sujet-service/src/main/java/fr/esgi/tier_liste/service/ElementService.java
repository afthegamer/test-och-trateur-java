package fr.esgi.tier_liste.service;

import fr.esgi.tier_liste.dto.ElementDto;
import java.util.List;

public interface ElementService {

    List<ElementDto> recupererElementsParSujetId(Long id);
}
