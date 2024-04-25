package dk.kea.dat3js.hogwarts5.errorhandling.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("NotFound error: " + message);
    }
}