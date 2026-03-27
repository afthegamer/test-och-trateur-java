package fr.esgi.tier_liste.dto;

import fr.esgi.tier_liste.business.Element;

import java.io.Serializable;

/**
 * DTO for {@link Element}
 */
public record ElementDto(Long id, String nom, Long sujetId) implements Serializable {
}