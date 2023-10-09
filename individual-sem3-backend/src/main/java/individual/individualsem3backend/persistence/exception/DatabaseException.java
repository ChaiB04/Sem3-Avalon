package individual.individualsem3backend.persistence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DatabaseException extends ResponseStatusException {
    public DatabaseException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Database error: " + message);
    }
}