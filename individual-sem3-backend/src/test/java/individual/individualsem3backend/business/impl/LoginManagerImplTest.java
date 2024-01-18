package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.configuration.security.token.AccessTokenEncoderDecoder;
import individual.individualsem3backend.configuration.security.token.impl.AccessTokenImpl;
import individual.individualsem3backend.domain.enumeration.Role;
import individual.individualsem3backend.external.GoogleApi;
import individual.individualsem3backend.persistence.GoogleUserRepository;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.GoogleUserEntity;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginManagerImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoderDecoder accessTokenEncoderDecoder;

    @Mock
    private GoogleUserRepository googleUserRepository;

    @Mock
    private GoogleApi googleApi;

    @InjectMocks
    private LoginManagerImpl loginManager;

    @Test
    void userLogsIn_Successfully_ReturnsUser(){
        String email = "Neuvi@gmail.com";
        String password = "idontlikefurina";
        // Encode the password
        String encodedPassword = "asdfasdfadf!";

        UserEntity userEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password(encodedPassword).country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF")
                .role(Role.CUSTOMER).phonenumber("9039032").build();


        when(userRepositoryMock.findByEmail(email)).thenReturn(userEntity);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        //when(passwordEncoder.encode(any())).thenReturn(encodedPassword);
        when(accessTokenEncoderDecoder.encode(new AccessTokenImpl(userEntity.getEmail(), userEntity.getId(), userEntity.getRole().toString()))).thenReturn("accesstoken");

        String accessToken = loginManager.userLogin(email, password);

        verify(userRepositoryMock).findByEmail(email);
        assertEquals("accesstoken", accessToken);
    }

    @Test
    void userLogsIn_WithNoInput_ReturnsUserException(){
        assertThrows(UserException.class, () -> loginManager.userLogin(null, null));
    }


    @Test
    void userLogsIn_Unsuccessfully_WithNoExistingAccount_ReturnsUserException(){
        String email = "Neuvi@gmail.com";
        String password = "idontlikefurina";

        when(userRepositoryMock.findByEmail(email)).thenReturn(null);

        assertThrows(UserException.class, () -> loginManager.userLogin(email, password));
    }

    @Test
    void userLogsIn_WithInvalidCredentials_ReturnsUserException(){
        String email = "Neuvi@gmail.com";
        String password = "idontlikefurina";
        // Encode the password
        String encodedPassword = "asdfasdfadf!";

        UserEntity userEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password(encodedPassword).country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF")
                .role(Role.CUSTOMER).phonenumber("9039032").build();


        when(userRepositoryMock.findByEmail(email)).thenReturn(userEntity);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        assertThrows(UserException.class, () -> loginManager.userLogin(email, password));
        verify(passwordEncoder).matches(password, encodedPassword);
        verify(userRepositoryMock).findByEmail(email);

    }

    @Test
    void loginWithGoogleAccount_Successful(){
        String accesstoken = "accessgranted";

        String sub = "sub";

        String encodedPassword = "asdfasdfadf!";

        GoogleUserEntity googleEntity = GoogleUserEntity.builder()
                .sub("sub")
                .user_id(1)
                .id(1).build();

        UserEntity userEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password(encodedPassword).country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF")
                .role(Role.CUSTOMER).phonenumber("9039032").build();


        when(googleApi.getSub(accesstoken)).thenReturn(sub);
        when(googleUserRepository.getGoogleUserEntityBySub(sub)).thenReturn(googleEntity);
        when(userRepositoryMock.findById(1)).thenReturn(Optional.of(userEntity));
        when(accessTokenEncoderDecoder.encode(new AccessTokenImpl(userEntity.getEmail(), userEntity.getId(), userEntity.getRole().toString()))).thenReturn("accesstoken");

        String result = loginManager.loginWithGoogleAccount(accesstoken);
        assertEquals("accesstoken", result);

    }

    @Test
    void loginWithGoogleAccount_ThrowsException_CannotFindUserInUserRepo(){
        String accesstoken = "accessgranted";

        String sub = "sub";

        GoogleUserEntity googleEntity = GoogleUserEntity.builder()
                .sub("sub")
                .user_id(1)
                .id(1).build();

        when(googleApi.getSub(accesstoken)).thenReturn(sub);
        when(googleUserRepository.getGoogleUserEntityBySub(sub)).thenReturn(googleEntity);
        when(userRepositoryMock.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> loginManager.loginWithGoogleAccount(accesstoken));

    }
}
