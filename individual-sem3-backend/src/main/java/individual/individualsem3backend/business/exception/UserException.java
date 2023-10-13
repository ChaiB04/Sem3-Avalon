package individual.individualsem3backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserException extends ResponseStatusException {

    public UserException() {super(HttpStatus.BAD_REQUEST, "Something went wrong with the user.");}

    public UserException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
