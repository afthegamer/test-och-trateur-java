package fr.esgi.tier_liste.service.impl;

import fr.esgi.tier_liste.business.Sujet;
import fr.esgi.tier_liste.dto.SujetDto;
import fr.esgi.tier_liste.mapper.SujetMapper;
import fr.esgi.tier_liste.repository.SujetRepository;
import fr.esgi.tier_liste.service.SujetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SujetServiceImpl implements SujetService {

    private final SujetRepository sujetRepository;
    private final SujetMapper sujetMapper;

    @Value("${exemple}")
    private String exemple;

    @Override
    public SujetDto ajouterSujet(SujetDto sujetDto) {
        return sujetMapper.toDto(sujetRepository.save(sujetMapper.toEntity(sujetDto)));
    }

    @Override
    public SujetDto recupererSujet(Long id) {
        return sujetMapper.toDto(sujetRepository.findById(id).orElse(null));
    }

    @Override
    public List<SujetDto> recupererSujets() {
        return sujetMapper.toDto(sujetRepository.findAll());
    }

    @Override
    public SujetDto patcherSujet(Long id, SujetDto sujetDto) {
        Sujet sujet = sujetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Le sujet n'existe pas"));
        sujet = sujetRepository.save(sujetMapper.partialUpdate(sujetDto, sujet));
        return sujetMapper.toDto(sujet);
    }

    @Override
    public void supprimerSujet(Long id) {
        sujetRepository.findById(id)
                .ifPresentOrElse(sujetRepository::delete, () -> {
                    throw new EntityNotFoundException("Le sujet n'existe pas");
                });
    }

    @Override
    public SujetDto mettreAJourSujet(Long id, SujetDto sujetDto) {
        if (sujetRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Le sujet n'existe pas");
        }
        Sujet sujetMisAJour = sujetMapper.toEntity(sujetDto);
        sujetMisAJour.setId(id);
        sujetRepository.save(sujetMisAJour);
        return sujetMapper.toDto(sujetMisAJour);
    }
}