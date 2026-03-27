package fr.esgi.utilisateur_service.controller.rest;

import fr.esgi.utilisateur_service.dto.UtilisateurDto;
import fr.esgi.utilisateur_service.mapper.UtilisateurMapper;
import fr.esgi.utilisateur_service.service.UtilisateurService;
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
    public ResponseEntity<List<UtilisateurDto>> getAll() {
        return ResponseEntity.ok(utilisateurService.recupererTous()
                .stream()
                .map(utilisateurMapper::toDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getById(@PathVariable Long id) {
        return utilisateurService.recupererParId(id)
                .map(utilisateurMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(utilisateurService.compterUtilisateurs());
    }

    @GetMapping("/anniversaire")
    public ResponseEntity<List<UtilisateurDto>> getAnniversaire() {
        return ResponseEntity.ok(utilisateurService.recupererUtilisateursCelebrantLeurAnniversaire()
                .stream()
                .map(utilisateurMapper::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> create(@Valid @RequestBody UtilisateurDto dto) {
        var utilisateur = utilisateurMapper.toEntity(dto);
        var saved = utilisateurService.ajouterUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> update(@PathVariable Long id, @Valid @RequestBody UtilisateurDto dto) {
        var updated = utilisateurMapper.toEntity(dto);
        return utilisateurService.modifierUtilisateur(id, updated)
                .map(utilisateurMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return utilisateurService.supprimerUtilisateur(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
