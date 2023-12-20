package individual.individualsem3backend.external;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class GoogleApi {
    private final RestTemplate restTemplate;

    public GoogleApi() {
        restTemplate = new RestTemplate();
    }

}
