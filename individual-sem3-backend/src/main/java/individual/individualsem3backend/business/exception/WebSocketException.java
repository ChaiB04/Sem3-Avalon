package individual.individualsem3backend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WebSocketException  extends ResponseStatusException {
    public WebSocketException(String message) {super(HttpStatus.NOT_FOUND, message);}
}
