package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginManagerImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private LoginManagerImpl loginManager;

//    @Test
//    public void userLogsIn_Successfully_ReturnsUser(){
//        String email = "Neuvi@gmail.com";
//        String password = "idontlikefurina";
//
//        UserEntity userEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
//                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
//                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();
//
//
//        when(userRepositoryMock.findByEmailAndPassword(email, password)).thenReturn(userEntity);
//
//        String accessToken = loginManager.userLogin(email, password);
//
//        verify(userRepositoryMock).findByEmailAndPassword(email, password);
//    }

    @Test
    public void userLogsIn_WithNoInput_ReturnsUserException(){
        assertThrows(UserException.class, () -> loginManager.userLogin(null, null));
    }
//    @Test
//    public void userLogsIn_Unsuccessfully_ReturnsNull(){
//        String email = "Neuvi@gmail.com";
//        String password = "idontlikefurina";
//
//        when(userRepositoryMock.findByEmailAndPassword(email, password)).thenReturn(null);
//
//        assertThrows(UserException.class, () -> loginManager.userLogin(email, password));
//
//    }

}
