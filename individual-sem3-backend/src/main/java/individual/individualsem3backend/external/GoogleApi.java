package individual.individualsem3backend.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleApi {
    private final RestTemplate restTemplate;

    public GoogleApi() {
        restTemplate = new RestTemplate();
    }

}
