package fr.esgi.categorie_service.controller.rest;

import fr.esgi.categorie_service.dto.CategorieDto;
import fr.esgi.categorie_service.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategorieRestController {

    private final CategorieService categorieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ajoute une nouvelle catégorie")
    public CategorieDto postCategorie(@RequestBody CategorieDto dto) {
        return categorieService.ajouterCategorie(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une catégorie par son ID")
    public CategorieDto getCategorieById(@PathVariable Long id) {
        return categorieService.recupererCategorie(id);
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les catégories")
    public List<CategorieDto> getCategories() {
        return categorieService.recupererCategories();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour partiellement la catégorie")
    public CategorieDto patchCategorie(@PathVariable Long id, @RequestBody CategorieDto dto) {
        return categorieService.patcherCategorie(id, dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remplace une catégorie par une autre")
    public CategorieDto putCategorie(@PathVariable Long id, @RequestBody CategorieDto dto) {
        return categorieService.mettreAJourCategorie(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une catégorie")
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.supprimerCategorie(id);
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
