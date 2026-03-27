package fr.esgi.colonne_service.mapper;

import fr.esgi.colonne_service.business.Colonne;
import fr.esgi.colonne_service.dto.ColonneDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-27T11:43:14+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class ColonneMapperImpl implements ColonneMapper {

    @Override
    public Colonne toEntity(ColonneDto dto) {
        if ( dto == null ) {
            return null;
        }

        Colonne colonne = new Colonne();

        colonne.setId( dto.id() );
        colonne.setNom( dto.nom() );
        colonne.setPosition( dto.position() );

        return colonne;
    }

    @Override
    public ColonneDto toDto(Colonne colonne) {
        if ( colonne == null ) {
            return null;
        }

        Long id = null;
        String nom = null;
        Integer position = null;

        id = colonne.getId();
        nom = colonne.getNom();
        position = colonne.getPosition();

        ColonneDto colonneDto = new ColonneDto( id, nom, position );

        return colonneDto;
    }

    @Override
    public Colonne partialUpdate(ColonneDto dto, Colonne colonne) {
        if ( dto == null ) {
            return colonne;
        }

        if ( dto.id() != null ) {
            colonne.setId( dto.id() );
        }
        if ( dto.nom() != null ) {
            colonne.setNom( dto.nom() );
        }
        if ( dto.position() != null ) {
            colonne.setPosition( dto.position() );
        }

        return colonne;
    }

    @Override
    public List<ColonneDto> toDto(List<Colonne> colonnes) {
        if ( colonnes == null ) {
            return null;
        }

        List<ColonneDto> list = new ArrayList<ColonneDto>( colonnes.size() );
        for ( Colonne colonne : colonnes ) {
            list.add( toDto( colonne ) );
        }

        return list;
    }
}
