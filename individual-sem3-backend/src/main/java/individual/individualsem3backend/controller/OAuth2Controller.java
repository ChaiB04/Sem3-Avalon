package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.business.OAuth2Manager;
import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.controller.dtos.oAuth.GetGoogleLink;
import individual.individualsem3backend.controller.dtos.oAuth.GetTokenExchange;
import individual.individualsem3backend.controller.dtos.oAuth.LinkGoogleAccount;
import individual.individualsem3backend.controller.dtos.user.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OAuth2Controller {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String client_id;

    @Autowired
    private OAuth2Manager oAuth2Manager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private LoginManager loginManager;

    @GetMapping()
    public ResponseEntity<GetGoogleLink> loginWithGoogleAccount(){

        try {
            String redirect = "https://accounts.google.com/o/oauth2/v2/auth?" +
                    "scope=" + URLEncoder.encode("https://www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",  "UTF-8") +
                    "&access_type=offline" +
                    "&include_granted_scopes=true" +
                    "&response_type=code" +
                    "&redirect_uri=" + URLEncoder.encode("http://localhost:5173/loadingLoggingIn", "UTF-8") +
                    "&client_id=" + client_id;

            GetGoogleLink link = GetGoogleLink.builder().link(redirect).build();

            return ResponseEntity.ok().body(link);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("link")
    public ResponseEntity<GetGoogleLink> linkWithGoogleAccount(){

        try {
            String redirect = "https://accounts.google.com/o/oauth2/v2/auth?" +
                    "scope=" + URLEncoder.encode("https://www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile",  "UTF-8") +
                    "&access_type=offline" +
                    "&include_granted_scopes=true" +
                    "&response_type=code" +
                    "&redirect_uri=" + URLEncoder.encode("http://localhost:5173/loadingLinking", "UTF-8") +
                    "&client_id=" + client_id;

            GetGoogleLink link = GetGoogleLink.builder().link(redirect).build();

            return ResponseEntity.ok().body(link);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> ExchangeCodeForToken(@RequestBody GetTokenExchange getTokenExchange){

        String accessToken = oAuth2Manager.receiveAccessTokenFromApi(getTokenExchange.getCode(), getTokenExchange.isLogin());

        return ResponseEntity.ok().body(accessToken);
    }


    @PostMapping("linkGoogle")
    public ResponseEntity<Void> linkGoogleAccount(@RequestBody LinkGoogleAccount linkaccounts){

        if(userManager.linkGoogleToAccount(linkaccounts.getUser_id(), linkaccounts.getAccessToken())){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("loginGoogle")
    public ResponseEntity<UserLoginResponse> loginWithGoogleResponse(@RequestBody GetTokenExchange code){
        String accessToken = loginManager.loginWithGoogleAccount(code.getCode());

        System.out.println("Accesstoken from avalon: " + accessToken);
        if(accessToken != null){
            return ResponseEntity.ok().body(UserLoginResponse.builder().accessToken(accessToken).build());
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

