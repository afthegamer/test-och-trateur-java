package fr.esgi.categorie_service.controller.rest;

import fr.esgi.categorie_service.dto.CategorieDto;
import fr.esgi.categorie_service.service.CategorieService;
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
@RequestMapping("/api/categories")
public class CategorieRestController {

    private final CategorieService categorieService;

    @PostMapping
    @Operation(summary = "Ajoute une nouvelle catégorie")
    public ResponseEntity<CategorieDto> postCategorie(@RequestBody CategorieDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categorieService.ajouterCategorie(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une catégorie par son ID")
    public ResponseEntity<CategorieDto> getCategorieById(@Parameter(description = "id de la catégorie") @PathVariable Long id) {
        return ResponseEntity.ok(categorieService.recupererCategorie(id));
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les catégories")
    public ResponseEntity<List<CategorieDto>> getCategories() {
        return ResponseEntity.ok(categorieService.recupererCategories());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour partiellement la catégorie")
    public ResponseEntity<CategorieDto> patchCategorie(@Parameter(description = "id de la catégorie") @PathVariable Long id, @RequestBody CategorieDto dto) {
        return ResponseEntity.ok(categorieService.patcherCategorie(id, dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remplace une catégorie par une autre")
    public ResponseEntity<CategorieDto> putCategorie(@Parameter(description = "id de la catégorie") @PathVariable Long id, @RequestBody CategorieDto dto) {
        return ResponseEntity.ok(categorieService.mettreAJourCategorie(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime une catégorie")
    public ResponseEntity<Void> deleteCategorie(@Parameter(description = "id de la catégorie") @PathVariable Long id) {
        categorieService.supprimerCategorie(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String traiterCategorieInvalide(ConstraintViolationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String traiterCategorieInexistante(EntityNotFoundException e) {
        return e.getMessage();
    }
}
