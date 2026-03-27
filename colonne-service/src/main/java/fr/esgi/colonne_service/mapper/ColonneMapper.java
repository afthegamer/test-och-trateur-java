package fr.esgi.colonne_service.mapper;

import fr.esgi.colonne_service.business.Colonne;
import fr.esgi.colonne_service.dto.ColonneDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColonneMapper {

    Colonne toEntity(ColonneDto dto);

    ColonneDto toDto(Colonne colonne);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Colonne partialUpdate(ColonneDto dto, @MappingTarget Colonne colonne);

    List<ColonneDto> toDto(List<Colonne> colonnes);
}
