package fr.esgi.tier_liste.service.impl;

import fr.esgi.tier_liste.mapper.ElementMapper;
import fr.esgi.tier_liste.dto.ElementDto;
import fr.esgi.tier_liste.repository.ElementRepository;
import fr.esgi.tier_liste.service.ElementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ElementServiceImpl implements ElementService {

    private ElementRepository elementRepository;
    private ElementMapper elementMapper;

    @Override
    public List<ElementDto> recupererElementsParSujetId(Long id) {
        return elementMapper.toDto(elementRepository.findBySujetId(id));
    }
}
