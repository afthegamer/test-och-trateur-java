package fr.esgi.tier_liste.controller.rest;

import fr.esgi.tier_liste.business.Sujet;
import fr.esgi.tier_liste.dto.ElementDto;
import fr.esgi.tier_liste.dto.SujetDto;
import fr.esgi.tier_liste.service.ElementService;
import fr.esgi.tier_liste.service.SujetService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/sujets")
public class SujetRestController {

    private SujetService sujetService;
    private ElementService elementService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Ajoute un nouveau sujet")
    public SujetDto postSujet(@RequestBody SujetDto sujetDto){
        return sujetService.ajouterSujet(sujetDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupère un sujet par son ID")
    public SujetDto getSujetById(@PathVariable Long id){
        return sujetService.recupererSujet(id);
    }

    @GetMapping("/{id}/elements")
    @Operation(summary = "Recupère les éléments d'un sujet dont l'id est donné en paramètre")
    public List<ElementDto> getElementsBySujetById(@PathVariable Long id){
        return elementService.recupererElementsParSujetId(id);
    }

    @GetMapping
    @Operation(summary = "Récupère tous les sujets")
    public List<SujetDto> getSujets() {
        return sujetService.recupererSujets();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour partiellement le sujet")
    public SujetDto patchSujet(@PathVariable Long id, @RequestBody SujetDto sujetDto) {
        return sujetService.patcherSujet(id, sujetDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Supprime un sujet")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSujet(@PathVariable Long id){
        sujetService.supprimerSujet(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remplace un sujet par un autre")
    public SujetDto putSujet(@PathVariable Long id, @RequestBody SujetDto sujetDto) {
        return sujetService.mettreAJourSujet(id, sujetDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String traiterSujetInvalide(ConstraintViolationException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String traiterSujetInexistant(EntityNotFoundException exception){
        return exception.getMessage();
    }

}
