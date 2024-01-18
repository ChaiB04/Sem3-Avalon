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
}
