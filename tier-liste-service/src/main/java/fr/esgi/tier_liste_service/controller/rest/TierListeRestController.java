package fr.esgi.tier_liste_service.controller.rest;

import fr.esgi.tier_liste_service.dto.TierListeDto;
import fr.esgi.tier_liste_service.service.TierListeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Place un élément dans une catégorie pour un utilisateur")
    public ResponseEntity<TierListeDto> post(@Valid @RequestBody TierListeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tierListeService.ajouterTierListe(dto));
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les tier lists")
    public ResponseEntity<List<TierListeDto>> getAll() {
        return ResponseEntity.ok(tierListeService.recupererToutes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une tier list par son ID")
    public ResponseEntity<TierListeDto> getById(@Parameter(description = "id de la tier liste") @PathVariable Long id) {
        return tierListeService.recupererParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    @Operation(summary = "Récupère les tier lists d'un utilisateur")
    public ResponseEntity<List<TierListeDto>> getByUtilisateur(@Parameter(description = "id de l'utilisateur") @PathVariable Long utilisateurId) {
        return ResponseEntity.ok(tierListeService.recupererParUtilisateur(utilisateurId));
    }

    @GetMapping("/utilisateur/{utilisateurId}/categorie/{categorieId}")
    @Operation(summary = "Récupère les tier lists d'un utilisateur pour une catégorie donnée")
    public ResponseEntity<List<TierListeDto>> getByUtilisateurAndCategorie(
            @Parameter(description = "id de l'utilisateur") @PathVariable Long utilisateurId,
            @Parameter(description = "id de la catégorie") @PathVariable Long categorieId) {
        return ResponseEntity.ok(tierListeService.recupererParUtilisateurEtCategorie(utilisateurId, categorieId));
    }

    @PatchMapping("/{id}/categorie/{categorieId}")
    @Operation(summary = "Déplace un élément vers une autre catégorie")
    public ResponseEntity<TierListeDto> patchCategorie(
            @Parameter(description = "id de la tier liste") @PathVariable Long id,
            @Parameter(description = "id de la nouvelle catégorie") @PathVariable Long categorieId) {
        return tierListeService.modifierCategorie(id, categorieId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime une tier list")
    public ResponseEntity<Void> delete(@Parameter(description = "id de la tier liste") @PathVariable Long id) {
        tierListeService.supprimerTierListe(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String traiterInexistant(EntityNotFoundException e) {
        return e.getMessage();
    }
}
