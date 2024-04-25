package dk.kea.dat3js.hogwarts5.errorhandling.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Validation error: " + message);
    }
}
