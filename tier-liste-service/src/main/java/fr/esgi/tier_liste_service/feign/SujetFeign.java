package fr.esgi.tier_liste_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "SUJET-SERVICE")
public interface SujetFeign {

    @GetMapping("/api/sujets")
    List<Object> recupererSujets();

    @GetMapping("/api/sujets/{id}")
    Object recupererSujet(@PathVariable Long id);
}
