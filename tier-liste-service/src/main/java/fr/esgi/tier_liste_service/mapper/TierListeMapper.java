package fr.esgi.tier_liste_service.mapper;

import fr.esgi.tier_liste_service.business.TierListe;
import fr.esgi.tier_liste_service.dto.TierListeDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TierListeMapper {

    TierListe toEntity(TierListeDto dto);

    TierListeDto toDto(TierListe tierListe);

    List<TierListeDto> toDto(List<TierListe> tierListes);
}
