package fr.esgi.tier_liste_service.service.impl;

import fr.esgi.tier_liste_service.dto.TierListeDto;
import fr.esgi.tier_liste_service.feign.SujetFeign;
import fr.esgi.tier_liste_service.feign.UtilisateurFeign;
import fr.esgi.tier_liste_service.mapper.TierListeMapper;
import fr.esgi.tier_liste_service.repository.TierListeRepository;
import fr.esgi.tier_liste_service.service.TierListeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierListeServiceImpl implements TierListeService {

    private final UtilisateurFeign utilisateurFeign;
    private final SujetFeign sujetFeign;
    private final TierListeRepository tierListeRepository;
    private final TierListeMapper tierListeMapper;

    @Override
    public TierListeDto ajouterTierListe(TierListeDto dto) {
        return tierListeMapper.toDto(tierListeRepository.save(tierListeMapper.toEntity(dto)));
    }

    @Override
    public Optional<TierListeDto> recupererParId(Long id) {
        return tierListeRepository.findById(id).map(tierListeMapper::toDto);
    }

    @Override
    public List<TierListeDto> recupererToutes() {
        return tierListeMapper.toDto(tierListeRepository.findAll());
    }

    @Override
    public List<TierListeDto> recupererParUtilisateur(Long utilisateurId) {
        return tierListeMapper.toDto(tierListeRepository.findByUtilisateurId(utilisateurId));
    }

    @Override
    public List<TierListeDto> recupererParUtilisateurEtCategorie(Long utilisateurId, Long categorieId) {
        return tierListeMapper.toDto(tierListeRepository.findByUtilisateurIdAndCategorieId(utilisateurId, categorieId));
    }

    @Override
    public Optional<TierListeDto> modifierCategorie(Long id, Long categorieId) {
        return tierListeRepository.findById(id)
                .map(tl -> {
                    tl.setCategorieId(categorieId);
                    return tierListeMapper.toDto(tierListeRepository.save(tl));
                });
    }

    @Override
    public void supprimerTierListe(Long id) {
        tierListeRepository.findById(id)
                .ifPresentOrElse(tierListeRepository::delete, () -> {
                    throw new EntityNotFoundException("La tier liste n'existe pas");
                });
    }
}
