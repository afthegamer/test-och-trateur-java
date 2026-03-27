package fr.esgi.utilisateur_service.validator;

import fr.esgi.utilisateur_service.annotation.DixHuitAnsOuPlus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class DixHuitAnsOuPlusValidator implements ConstraintValidator<DixHuitAnsOuPlus, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateDeNaissance, ConstraintValidatorContext context) {
        return dateDeNaissance != null && YEARS.between(dateDeNaissance, LocalDate.now()) >= 18;
    }
}
