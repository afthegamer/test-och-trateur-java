package fr.esgi.colonne_service.service.impl;

import fr.esgi.colonne_service.dto.ColonneDto;
import fr.esgi.colonne_service.mapper.ColonneMapper;
import fr.esgi.colonne_service.repository.ColonneRepository;
import fr.esgi.colonne_service.service.ColonneService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColonneServiceImpl implements ColonneService {

    private final ColonneRepository colonneRepository;
    private final ColonneMapper colonneMapper;

    @Override
    public ColonneDto ajouterColonne(ColonneDto dto) {
        return colonneMapper.toDto(colonneRepository.save(colonneMapper.toEntity(dto)));
    }

    @Override
    public ColonneDto recupererColonne(Long id) {
        return colonneRepository.findById(id)
                .map(colonneMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("La colonne n'existe pas"));
    }

    @Override
    public List<ColonneDto> recupererColonnes() {
        return colonneMapper.toDto(colonneRepository.findAll());
    }

    @Override
    public ColonneDto patcherColonne(Long id, ColonneDto dto) {
        var colonne = colonneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La colonne n'existe pas"));
        return colonneMapper.toDto(colonneRepository.save(colonneMapper.partialUpdate(dto, colonne)));
    }

    @Override
    public ColonneDto mettreAJourColonne(Long id, ColonneDto dto) {
        if (colonneRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("La colonne n'existe pas");
        }
        var colonne = colonneMapper.toEntity(dto);
        colonne.setId(id);
        return colonneMapper.toDto(colonneRepository.save(colonne));
    }

    @Override
    public void supprimerColonne(Long id) {
        colonneRepository.findById(id)
                .ifPresentOrElse(colonneRepository::delete, () -> {
                    throw new EntityNotFoundException("La colonne n'existe pas");
                });
    }
}
