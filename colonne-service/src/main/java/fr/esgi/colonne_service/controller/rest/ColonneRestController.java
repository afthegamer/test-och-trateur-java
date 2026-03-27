package fr.esgi.colonne_service.controller.rest;

import fr.esgi.colonne_service.dto.ColonneDto;
import fr.esgi.colonne_service.service.ColonneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/colonnes")
public class ColonneRestController {

    private final ColonneService colonneService;

    @PostMapping
    @Operation(summary = "Ajoute une nouvelle colonne")
    public ResponseEntity<ColonneDto> postColonne(@RequestBody ColonneDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colonneService.ajouterColonne(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une colonne par son ID")
    public ResponseEntity<ColonneDto> getColonneById(@Parameter(description = "id de la colonne") @PathVariable Long id) {
        return ResponseEntity.ok(colonneService.recupererColonne(id));
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les colonnes")
    public ResponseEntity<List<ColonneDto>> getColonnes() {
        return ResponseEntity.ok(colonneService.recupererColonnes());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour partiellement la colonne")
    public ResponseEntity<ColonneDto> patchColonne(@Parameter(description = "id de la colonne") @PathVariable Long id, @RequestBody ColonneDto dto) {
        return ResponseEntity.ok(colonneService.patcherColonne(id, dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remplace une colonne par une autre")
    public ResponseEntity<ColonneDto> putColonne(@Parameter(description = "id de la colonne") @PathVariable Long id, @RequestBody ColonneDto dto) {
        return ResponseEntity.ok(colonneService.mettreAJourColonne(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime une colonne")
    public ResponseEntity<Void> deleteColonne(@Parameter(description = "id de la colonne") @PathVariable Long id) {
        colonneService.supprimerColonne(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String traiterColonneInvalide(ConstraintViolationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String traiterColonneInexistante(EntityNotFoundException e) {
        return e.getMessage();
    }
}
