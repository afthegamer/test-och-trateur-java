package fr.esgi.tier_liste_service.mapper;

import fr.esgi.tier_liste_service.business.TierListe;
import fr.esgi.tier_liste_service.dto.TierListeDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-27T11:43:34+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class TierListeMapperImpl implements TierListeMapper {

    @Override
    public TierListe toEntity(TierListeDto dto) {
        if ( dto == null ) {
            return null;
        }

        TierListe tierListe = new TierListe();

        tierListe.setId( dto.id() );
        tierListe.setUtilisateurId( dto.utilisateurId() );
        tierListe.setElementId( dto.elementId() );
        tierListe.setCategorieId( dto.categorieId() );
        tierListe.setDateHeurePlacement( dto.dateHeurePlacement() );

        return tierListe;
    }

    @Override
    public TierListeDto toDto(TierListe tierListe) {
        if ( tierListe == null ) {
            return null;
        }

        Long id = null;
        Long utilisateurId = null;
        Long elementId = null;
        Long categorieId = null;
        LocalDateTime dateHeurePlacement = null;

        id = tierListe.getId();
        utilisateurId = tierListe.getUtilisateurId();
        elementId = tierListe.getElementId();
        categorieId = tierListe.getCategorieId();
        dateHeurePlacement = tierListe.getDateHeurePlacement();

        TierListeDto tierListeDto = new TierListeDto( id, utilisateurId, elementId, categorieId, dateHeurePlacement );

        return tierListeDto;
    }

    @Override
    public List<TierListeDto> toDto(List<TierListe> tierListes) {
        if ( tierListes == null ) {
            return null;
        }

        List<TierListeDto> list = new ArrayList<TierListeDto>( tierListes.size() );
        for ( TierListe tierListe : tierListes ) {
            list.add( toDto( tierListe ) );
        }

        return list;
    }
}
