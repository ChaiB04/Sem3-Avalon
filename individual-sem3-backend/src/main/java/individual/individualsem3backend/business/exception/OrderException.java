package individual.individualsem3backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderException  extends ResponseStatusException {

    public OrderException(String message) {super(HttpStatus.BAD_REQUEST, message);}
}
