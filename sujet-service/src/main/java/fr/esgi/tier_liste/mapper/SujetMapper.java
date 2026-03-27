package fr.esgi.tier_liste.mapper;

import fr.esgi.tier_liste.business.Sujet;
import fr.esgi.tier_liste.dto.SujetDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SujetMapper {
    Sujet toEntity(SujetDto sujetDto);

    SujetDto toDto(Sujet sujet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sujet partialUpdate(SujetDto sujetDto, @MappingTarget Sujet sujet);

    List<Sujet> toEntity(List<SujetDto> sujetDto);

    List<SujetDto> toDto(List<Sujet> sujet);
}