package fr.esgi.utilisateur_service.annotation;

import fr.esgi.utilisateur_service.validator.DixHuitAnsOuPlusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DixHuitAnsOuPlusValidator.class)
public @interface DixHuitAnsOuPlus {

    String message() default "Vous devez être majeur pour vous inscrire";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
