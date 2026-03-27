package fr.esgi.colonne_service.controller.rest;

import fr.esgi.colonne_service.dto.ColonneDto;
import fr.esgi.colonne_service.service.ColonneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/colonnes")
public class ColonneRestController {

    private final ColonneService colonneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ajoute une nouvelle colonne")
    public ColonneDto postColonne(@RequestBody ColonneDto dto) {
        return colonneService.ajouterColonne(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une colonne par son ID")
    public ColonneDto getColonneById(@PathVariable Long id) {
        return colonneService.recupererColonne(id);
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les colonnes")
    public List<ColonneDto> getColonnes() {
        return colonneService.recupererColonnes();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour partiellement la colonne")
    public ColonneDto patchColonne(@PathVariable Long id, @RequestBody ColonneDto dto) {
        return colonneService.patcherColonne(id, dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remplace une colonne par une autre")
    public ColonneDto putColonne(@PathVariable Long id, @RequestBody ColonneDto dto) {
        return colonneService.mettreAJourColonne(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une colonne")
    public void deleteColonne(@PathVariable Long id) {
        colonneService.supprimerColonne(id);
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
