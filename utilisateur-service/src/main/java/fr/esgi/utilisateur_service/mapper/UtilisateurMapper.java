package fr.esgi.utilisateur_service.mapper;

import fr.esgi.utilisateur_service.business.Utilisateur;
import fr.esgi.utilisateur_service.dto.UtilisateurDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(source = "theme.id", target = "themeId")
    UtilisateurDto toDto(Utilisateur utilisateur);

    @Mapping(source = "themeId", target = "theme.id")
    @Mapping(target = "dateHeureInscription", ignore = true)
    @Mapping(target = "dateHeureModification", ignore = true)
    @Mapping(target = "interets", ignore = true)
    Utilisateur toEntity(UtilisateurDto dto);
}
