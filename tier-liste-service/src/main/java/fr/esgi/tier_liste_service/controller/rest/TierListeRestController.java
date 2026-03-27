package fr.esgi.tier_liste_service.controller.rest;

import fr.esgi.tier_liste_service.dto.TierListeDto;
import fr.esgi.tier_liste_service.service.TierListeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tier-listes")
public class TierListeRestController {

    private final TierListeService tierListeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Place un élément dans une catégorie pour un utilisateur")
    public TierListeDto post(@Valid @RequestBody TierListeDto dto) {
        return tierListeService.ajouterTierListe(dto);
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les tier lists")
    public List<TierListeDto> getAll() {
        return tierListeService.recupererToutes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une tier list par son ID")
    public ResponseEntity<TierListeDto> getById(@PathVariable Long id) {
        return tierListeService.recupererParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    @Operation(summary = "Récupère les tier lists d'un utilisateur")
    public List<TierListeDto> getByUtilisateur(@PathVariable Long utilisateurId) {
        return tierListeService.recupererParUtilisateur(utilisateurId);
    }

    @GetMapping("/utilisateur/{utilisateurId}/categorie/{categorieId}")
    @Operation(summary = "Récupère les tier lists d'un utilisateur pour une catégorie donnée")
    public List<TierListeDto> getByUtilisateurAndCategorie(
            @PathVariable Long utilisateurId,
            @PathVariable Long categorieId) {
        return tierListeService.recupererParUtilisateurEtCategorie(utilisateurId, categorieId);
    }

    @PatchMapping("/{id}/categorie/{categorieId}")
    @Operation(summary = "Déplace un élément vers une autre catégorie")
    public ResponseEntity<TierListeDto> patchCategorie(
            @PathVariable Long id,
            @PathVariable Long categorieId) {
        return tierListeService.modifierCategorie(id, categorieId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une tier list")
    public void delete(@PathVariable Long id) {
        tierListeService.supprimerTierListe(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String traiterInexistant(EntityNotFoundException e) {
        return e.getMessage();
    }
}
