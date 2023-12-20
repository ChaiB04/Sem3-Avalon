package individual.individualsem3backend.business.impl;

import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.CLIENT_ID;

@Service
public class OAuth2ManagerImpl {

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
