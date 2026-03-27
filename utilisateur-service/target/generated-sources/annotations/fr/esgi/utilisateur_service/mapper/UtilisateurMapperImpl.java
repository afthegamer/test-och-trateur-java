package fr.esgi.utilisateur_service.mapper;

import fr.esgi.utilisateur_service.business.Theme;
import fr.esgi.utilisateur_service.business.Utilisateur;
import fr.esgi.utilisateur_service.dto.UtilisateurDto;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-27T11:41:49+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public UtilisateurDto toDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        Long themeId = null;
        Long id = null;
        String prenom = null;
        String nom = null;
        String email = null;
        String password = null;
        LocalDate dateDeNaissance = null;

        themeId = utilisateurThemeId( utilisateur );
        id = utilisateur.getId();
        prenom = utilisateur.getPrenom();
        nom = utilisateur.getNom();
        email = utilisateur.getEmail();
        password = utilisateur.getPassword();
        dateDeNaissance = utilisateur.getDateDeNaissance();

        UtilisateurDto utilisateurDto = new UtilisateurDto( id, prenom, nom, email, password, dateDeNaissance, themeId );

        return utilisateurDto;
    }

    @Override
    public Utilisateur toEntity(UtilisateurDto dto) {
        if ( dto == null ) {
            return null;
        }

        Utilisateur.UtilisateurBuilder<?, ?> utilisateur = Utilisateur.builder();

        utilisateur.theme( utilisateurDtoToTheme( dto ) );
        utilisateur.id( dto.id() );
        utilisateur.prenom( dto.prenom() );
        utilisateur.nom( dto.nom() );
        utilisateur.email( dto.email() );
        utilisateur.password( dto.password() );
        utilisateur.dateDeNaissance( dto.dateDeNaissance() );

        return utilisateur.build();
    }

    private Long utilisateurThemeId(Utilisateur utilisateur) {
        Theme theme = utilisateur.getTheme();
        if ( theme == null ) {
            return null;
        }
        return theme.getId();
    }

    protected Theme utilisateurDtoToTheme(UtilisateurDto utilisateurDto) {
        if ( utilisateurDto == null ) {
            return null;
        }

        Theme theme = new Theme();

        theme.setId( utilisateurDto.themeId() );

        return theme;
    }
}
