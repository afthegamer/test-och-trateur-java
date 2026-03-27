package fr.esgi.categorie_service.mapper;

import fr.esgi.categorie_service.business.Categorie;
import fr.esgi.categorie_service.dto.CategorieDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-27T11:42:48+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class CategorieMapperImpl implements CategorieMapper {

    @Override
    public Categorie toEntity(CategorieDto dto) {
        if ( dto == null ) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setId( dto.id() );
        categorie.setNom( dto.nom() );
        categorie.setRang( dto.rang() );

        return categorie;
    }

    @Override
    public CategorieDto toDto(Categorie categorie) {
        if ( categorie == null ) {
            return null;
        }

        Long id = null;
        String nom = null;
        Integer rang = null;

        id = categorie.getId();
        nom = categorie.getNom();
        rang = categorie.getRang();

        CategorieDto categorieDto = new CategorieDto( id, nom, rang );

        return categorieDto;
    }

    @Override
    public Categorie partialUpdate(CategorieDto dto, Categorie categorie) {
        if ( dto == null ) {
            return categorie;
        }

        if ( dto.id() != null ) {
            categorie.setId( dto.id() );
        }
        if ( dto.nom() != null ) {
            categorie.setNom( dto.nom() );
        }
        if ( dto.rang() != null ) {
            categorie.setRang( dto.rang() );
        }

        return categorie;
    }

    @Override
    public List<CategorieDto> toDto(List<Categorie> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategorieDto> list = new ArrayList<CategorieDto>( categories.size() );
        for ( Categorie categorie : categories ) {
            list.add( toDto( categorie ) );
        }

        return list;
    }
}
