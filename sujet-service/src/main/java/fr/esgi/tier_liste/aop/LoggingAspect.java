package fr.esgi.tier_liste.aop;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@AllArgsConstructor
@Log4j2
@Component
/**
 * La classe d'AOP permet d'externaliser un besoin technique, ici le besoin est de logger
 * ce qu'il se passe dans l'application
 */
public class LoggingAspect {

    // On demande à la classe d'AOP d'observer toutes les méthodes présentes
    // dans les classes annotées @Service
    //@Pointcut("@within(org.springframework.stereotype.Service)")
    // Exemple 2 :
    // observer les méthodes des classes annotées @Service
    // qui débutent par a
    //@Pointcut("@within(org.springframework.stereotype.Service) && execution(* a*(..))")
    // Exemple 3 : observer les méthodes des classes du package
    // fr.esgi.tier_liste.controller.rest
    // dont le nom débute par g et qui renvoient une liste de type java.util.List
    //@Pointcut("within(fr.esgi.tier_liste.controller.rest) && execution(java.util.List g*(..))" )
    //@Pointcut("within(fr.esgi.tier_liste.controller.rest.*) && execution(java.util.List g*(..))" )
    @Pointcut("within(fr.esgi.tier_liste.controller.rest..*) && execution(java.util.List g*(..))" )
    public void tiersListePointcut() {
    }

    @Around("tiersListePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            Object result = joinPoint.proceed();
            log.info("Invocation {} avec arguments {} : ", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            System.out.println("Invocation : " + joinPoint.getSignature().getName() + "() argument[s] = "
                    + Arrays.toString(joinPoint.getArgs())
                    + " resultat = " + result);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Exception levée : {} dans {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            System.err.println("Exception levée : " + e.getMessage());
            throw e;
        }
    }
}
