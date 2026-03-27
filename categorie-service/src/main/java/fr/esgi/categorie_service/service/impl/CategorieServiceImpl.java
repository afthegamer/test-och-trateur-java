package fr.esgi.categorie_service.service.impl;

import fr.esgi.categorie_service.dto.CategorieDto;
import fr.esgi.categorie_service.mapper.CategorieMapper;
import fr.esgi.categorie_service.repository.CategorieRepository;
import fr.esgi.categorie_service.service.CategorieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    @Override
    public CategorieDto ajouterCategorie(CategorieDto dto) {
        return categorieMapper.toDto(categorieRepository.save(categorieMapper.toEntity(dto)));
    }

    @Override
    public CategorieDto recupererCategorie(Long id) {
        return categorieRepository.findById(id)
                .map(categorieMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("La catégorie n'existe pas"));
    }

    @Override
    public List<CategorieDto> recupererCategories() {
        return categorieMapper.toDto(categorieRepository.findAll());
    }

    @Override
    public CategorieDto patcherCategorie(Long id, CategorieDto dto) {
        var categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La catégorie n'existe pas"));
        return categorieMapper.toDto(categorieRepository.save(categorieMapper.partialUpdate(dto, categorie)));
    }

    @Override
    public CategorieDto mettreAJourCategorie(Long id, CategorieDto dto) {
        if (categorieRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("La catégorie n'existe pas");
        }
        var categorie = categorieMapper.toEntity(dto);
        categorie.setId(id);
        return categorieMapper.toDto(categorieRepository.save(categorie));
    }

    @Override
    public void supprimerCategorie(Long id) {
        categorieRepository.findById(id)
                .ifPresentOrElse(categorieRepository::delete, () -> {
                    throw new EntityNotFoundException("La catégorie n'existe pas");
                });
    }
}
