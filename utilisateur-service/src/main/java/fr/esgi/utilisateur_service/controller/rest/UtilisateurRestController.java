package fr.esgi.utilisateur_service.controller.rest;

import fr.esgi.utilisateur_service.dto.UtilisateurDto;
import fr.esgi.utilisateur_service.mapper.UtilisateurMapper;
import fr.esgi.utilisateur_service.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@AllArgsConstructor
public class UtilisateurRestController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;

    @GetMapping
    @Operation(summary = "Récupère tous les utilisateurs")
    public ResponseEntity<List<UtilisateurDto>> getAll() {
        return ResponseEntity.ok(utilisateurService.recupererTous()
                .stream()
                .map(utilisateurMapper::toDto)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère l'utilisateur par son id")
    public ResponseEntity<UtilisateurDto> getById(@Parameter(description = "id de l'utilisateur") @PathVariable Long id) {
        return utilisateurService.recupererParId(id)
                .map(utilisateurMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    @Operation(summary = "Compter le nombre total d'utilisateurs")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(utilisateurService.compterUtilisateurs());
    }

    @GetMapping("/anniversaire")
    @Operation(summary = "Récupérer les utilisateurs qui célèbrent leur anniversaire aujourd'hui")
    public ResponseEntity<List<UtilisateurDto>> getAnniversaire() {
        return ResponseEntity.ok(utilisateurService.recupererUtilisateursCelebrantLeurAnniversaire()
                .stream()
                .map(utilisateurMapper::toDto)
                .toList());
    }

    @PostMapping
    @Operation(summary = "Ajouter un nouvel utilisateur")
    public ResponseEntity<UtilisateurDto> create(@Valid @RequestBody UtilisateurDto dto) {
        var utilisateur = utilisateurMapper.toEntity(dto);
        var saved = utilisateurService.ajouterUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un utilisateur")
    public ResponseEntity<UtilisateurDto> update(@Parameter(description = "id de l'utilisateur") @PathVariable Long id, @Valid @RequestBody UtilisateurDto dto) {
        var updated = utilisateurMapper.toEntity(dto);
        return utilisateurService.modifierUtilisateur(id, updated)
                .map(utilisateurMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    public ResponseEntity<Void> delete(@Parameter(description = "id de l'utilisateur") @PathVariable Long id) {
        return utilisateurService.supprimerUtilisateur(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
