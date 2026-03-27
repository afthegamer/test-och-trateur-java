package fr.esgi.utilisateur_service.service.impl;

import fr.esgi.utilisateur_service.business.Theme;
import fr.esgi.utilisateur_service.repository.ThemeRepository;
import fr.esgi.utilisateur_service.service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    public List<Theme> recupererThemes() {
        return themeRepository.findAll();
    }
}
