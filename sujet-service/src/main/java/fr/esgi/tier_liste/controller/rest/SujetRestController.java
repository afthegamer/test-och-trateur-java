package fr.esgi.tier_liste.controller.rest;

import fr.esgi.tier_liste.dto.ElementDto;
import fr.esgi.tier_liste.dto.SujetDto;
import fr.esgi.tier_liste.service.ElementService;
import fr.esgi.tier_liste.service.SujetService;
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
@RequestMapping("/api/sujets")
public class SujetRestController {

    private SujetService sujetService;
    private ElementService elementService;

    @PostMapping("")
    @Operation(summary = "Ajoute un nouveau sujet")
    public ResponseEntity<SujetDto> postSujet(@RequestBody SujetDto sujetDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sujetService.ajouterSujet(sujetDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupère un sujet par son ID")
    public ResponseEntity<SujetDto> getSujetById(@Parameter(description = "id du sujet") @PathVariable Long id) {
        return ResponseEntity.ok(sujetService.recupererSujet(id));
    }

    @GetMapping("/{id}/elements")
    @Operation(summary = "Recupère les éléments d'un sujet dont l'id est donné en paramètre")
    public ResponseEntity<List<ElementDto>> getElementsBySujetById(@Parameter(description = "id du sujet") @PathVariable Long id) {
        return ResponseEntity.ok(elementService.recupererElementsParSujetId(id));
    }

    @GetMapping
    @Operation(summary = "Récupère tous les sujets")
    public ResponseEntity<List<SujetDto>> getSujets() {
        return ResponseEntity.ok(sujetService.recupererSujets());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour partiellement le sujet")
    public ResponseEntity<SujetDto> patchSujet(@Parameter(description = "id du sujet") @PathVariable Long id, @RequestBody SujetDto sujetDto) {
        return ResponseEntity.ok(sujetService.patcherSujet(id, sujetDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime un sujet")
    public ResponseEntity<Void> deleteSujet(@Parameter(description = "id du sujet") @PathVariable Long id) {
        sujetService.supprimerSujet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remplace un sujet par un autre")
    public ResponseEntity<SujetDto> putSujet(@Parameter(description = "id du sujet") @PathVariable Long id, @RequestBody SujetDto sujetDto) {
        return ResponseEntity.ok(sujetService.mettreAJourSujet(id, sujetDto));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String traiterSujetInvalide(ConstraintViolationException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String traiterSujetInexistant(EntityNotFoundException exception) {
        return exception.getMessage();
    }

}
