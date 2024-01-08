package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OAuth2Manager;
import individual.individualsem3backend.external.GoogleApi;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OAuth2ManagerImpl implements OAuth2Manager {

    private GoogleApi googleApi;

    public String receiveAccessTokenFromApi(String code, boolean login){
       try{
           return googleApi.getAccessToken(code,login );
       }
       catch(Exception ex){
            throw new OAuth2AuthenticationException("Cannot receive access token from Google Api");
        }
    }




//    public  Scanner  stateToken(){
//        String state = new BigInteger(130, new SecureRandom()).toString(32);
//        request.session().attribute("state", state);
//// Read index.html into memory, and set the client ID,
//// token state, and application name in the HTML before serving it.
//        return new Scanner(new File("index.html"), "UTF-8")
//                .useDelimiter("\\A").next()
//                .replaceAll("[{]{2}\\s*CLIENT_ID\\s*[}]{2}", CLIENT_ID)
//                .replaceAll("[{]{2}\\s*STATE\\s*[}]{2}", state)
//                .replaceAll("[{]{2}\\s*APPLICATION_NAME\\s*[}]{2}",
//                        APPLICATION_NAME);
//    }
}
