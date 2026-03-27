package fr.esgi.categorie_service.mapper;

import fr.esgi.categorie_service.business.Categorie;
import fr.esgi.categorie_service.dto.CategorieDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategorieMapper {

    Categorie toEntity(CategorieDto dto);

    CategorieDto toDto(Categorie categorie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Categorie partialUpdate(CategorieDto dto, @MappingTarget Categorie categorie);

    List<CategorieDto> toDto(List<Categorie> categories);
}
