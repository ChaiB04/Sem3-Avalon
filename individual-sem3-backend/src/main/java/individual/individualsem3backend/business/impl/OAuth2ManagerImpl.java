package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OAuth2Manager;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.external.GoogleApi;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OAuth2ManagerImpl implements OAuth2Manager {

    private GoogleApi googleApi;

    public String receiveAccessTokenFromApi(String code){
       try{
           return googleApi.getAccessToken(code);
       }
       catch(Exception ex){

        }
       return null;
    }


//    public String loginWithGoogleAccount(String googleAccessToken){
//        String sub = googleApi.getSub(googleAccessToken);
//
//
//
//    }

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
