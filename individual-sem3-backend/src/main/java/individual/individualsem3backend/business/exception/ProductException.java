package individual.individualsem3backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductException extends ResponseStatusException {

    public ProductException() {super(HttpStatus.BAD_REQUEST, "Something went wrong with the product.");}

    public ProductException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
