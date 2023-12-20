package individual.individualsem3backend.controller;

import individual.individualsem3backend.controller.dtos.GetGoogleLink;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/oauth2login")
@RequiredArgsConstructor
public class OAuth2Controller {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String client_id;

    @GetMapping
    public ResponseEntity<GetGoogleLink> chooseAccount(){

        try {
            String redirect = "https://accounts.google.com/o/oauth2/v2/auth?" +
                    "scope=" + URLEncoder.encode("https://www.googleapis.com/auth/drive.metadata.readonly", "UTF-8") +
                    "&access_type=offline" +
                    "&include_granted_scopes=true" +
                    "&response_type=code" +
                    "&redirect_uri=" + URLEncoder.encode("http://localhost:5173/", "UTF-8") +
                    "&client_id=" + client_id;

            GetGoogleLink link = GetGoogleLink.builder().link(redirect).build();

            return ResponseEntity.ok().body(link);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

