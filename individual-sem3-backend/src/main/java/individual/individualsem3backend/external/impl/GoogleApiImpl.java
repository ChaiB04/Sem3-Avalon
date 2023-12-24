package individual.individualsem3backend.external.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.SignedJWT;
import individual.individualsem3backend.external.GoogleApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleApiImpl implements GoogleApi {
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String client_id;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String client_secret;


    public GoogleApiImpl() {
        restTemplate = new RestTemplate();
    }

    public String getAccessToken(String authorizationCode){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("code", authorizationCode);
            requestBody.add("client_id", client_id);
            requestBody.add("client_secret", client_secret);
            requestBody.add("redirect_uri", "http://localhost:5173/loading");
            requestBody.add("grant_type", "authorization_code");
//            requestBody.add("access_type", "offline");

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token", requestEntity, String.class);
            JsonElement jsonElement = JsonParser.parseString(responseEntity.getBody());
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String accessToken = jsonObject.get("access_token").getAsString();
            String refreshToken = jsonObject.get("refresh_token").getAsString();
//
//            System.out.println(accessToken);
            getInfoFromGoogle(accessToken);
//            refreshAccessToken(accessToken);

            return accessToken;
        }
        catch (Exception ex) {
            // Print or log the exception details
            ex.printStackTrace();
            System.out.println("Token exchange failed: " + ex.getMessage());
            return null;
        }
    }

    private JsonElement getInfoFromGoogle(String accessToken){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange("https://www.googleapis.com/oauth2/v3/userinfo", HttpMethod.GET, entity, String.class);

            JsonElement jsonElement = JsonParser.parseString(response.getBody());
            JsonObject jsonObject = jsonElement.getAsJsonObject();



            System.out.println(response);
            return jsonElement;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Token exchange failed: " + ex.getMessage());
        }
        return null;
    }

    public String getSub(String accessToken){

        JsonObject object = getInfoFromGoogle(accessToken).getAsJsonObject();

        String sub = object.get("sub").getAsString();

        return sub;
    }


//    private void refreshAccessToken(String refreshToken) {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//            requestBody.add("grant_type", "refresh_token");
//            requestBody.add("client_id", client_id);
//            requestBody.add("client_secret", client_secret);
//            requestBody.add("refresh_token", refreshToken);
//
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token", requestEntity, String.class);
//
//            // Parse the response to extract the new access token
//            // (Assuming the response is in JSON format)
//            String responseBody = responseEntity.getBody();
//            // Extract access token from responseBody (use JSON parsing library)
//
//            System.out.println("refresh token new: "+responseBody); // Replace with the actual access token
//        } catch (Exception ex) {
//            // Handle the exception (e.g., log, throw, etc.)
//            ex.printStackTrace();
//        }
//    }

}
