package fr.esgi.tier_liste_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "UTILISATEUR-SERVICE")
public interface UtilisateurFeign {

    @GetMapping("/api/utilisateurs/count")
    Long compterUtilisateurs();
}
