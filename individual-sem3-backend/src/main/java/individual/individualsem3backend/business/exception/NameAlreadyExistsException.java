package individual.individualsem3backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NameAlreadyExistsException extends ResponseStatusException {

    public NameAlreadyExistsException() {super(HttpStatus.BAD_REQUEST, "ID_ALREADY_EXISTS");}
}
