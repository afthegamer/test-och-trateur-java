package fr.esgi.utilisateur_service.exception;

public class EmailDejaPresentException extends RuntimeException {

    public EmailDejaPresentException(String message) {
        super(message);
    }
}
