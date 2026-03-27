package fr.esgi.tier_liste.mapper;

import fr.esgi.tier_liste.business.Element;
import fr.esgi.tier_liste.dto.ElementDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ElementMapper {
    @Mapping(source="sujetId", target="sujet.id")
    Element toEntity(ElementDto elementDto);

    @Mapping(source="sujet.id", target="sujetId")
    ElementDto toDto(Element element);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Element partialUpdate(ElementDto elementDto, @MappingTarget Element element);

    List<Element> toEntity(List<ElementDto> elementDto);

    List<ElementDto> toDto(List<Element> element);
}