package individual.individualsem3backend.business.impl;
import individual.individualsem3backend.external.GoogleApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OAuth2ManagerImplTest {

    @Mock
    private GoogleApi googleApi;

    @InjectMocks
    private OAuth2ManagerImpl oAuth2Manager;


    @Test
    void receiveAccessTokenFromApi_Successful(){
        String code = "HJKSDnjkdssuidHJNKSD";
        boolean login = true;

        String expectedResult = "yesworked";

        when(googleApi.getAccessToken(code, login)).thenReturn(expectedResult);

        String result = oAuth2Manager.receiveAccessTokenFromApi(code, login);

        assertEquals(expectedResult, result);
        verify(googleApi).getAccessToken(code, login);
    }

    @Test
    void receiveAccessTokenFromApi_ThrowsException(){
        String code = "HJKSDnjkdssuidHJNKSD";
        boolean login = true;

        when(googleApi.getAccessToken(code, login)).thenThrow(OAuth2AuthenticationException.class);

        assertThrows(OAuth2AuthenticationException.class, () -> oAuth2Manager.receiveAccessTokenFromApi(code, login));
    }

}
