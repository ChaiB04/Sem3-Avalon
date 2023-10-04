package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserManagerImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserManagerImpl userManager;

    @Test
    public void createUser_Successfully_ReturnUser(){
        User user = User.builder().firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        User expectedUser = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.save(user)).thenReturn(expectedUser);

        User actualResult = userManager.createUser(user);

        verify(userRepositoryMock).save(user);

        assertEquals(expectedUser, actualResult);
    }

    @Test
    public void userLogsIn_Successfully_ReturnsUser(){
        String email = "Neuvi@gmail.com";
        String password = "idontlikefurina";

        User expectedUser = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.findByEmailAndPassword(email, password)).thenReturn(expectedUser);

        User actualResult = userManager.userLogin(email, password);

        verify(userRepositoryMock).findByEmailAndPassword(email, password);

        assertEquals(expectedUser, actualResult);
    }

    @Test
    public void userLogsIn_Unsuccessfully_ReturnsNull(){
        String email = "Neuvi@gmail.com";
        String password = "idontlikefurina";

        when(userRepositoryMock.findByEmailAndPassword(email, password)).thenReturn(null);

        User actualResult = userManager.userLogin(email, password);

        verify(userRepositoryMock).findByEmailAndPassword(email, password);

        assertEquals(null, actualResult);
    }

    @Test
    public void getUser_Successfully_ReturnsUser(){
        User expectedUser = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.findUserById(1)).thenReturn(expectedUser);

        Optional<User> actualResult = userManager.getUser(1);

        verify(userRepositoryMock).findUserById(1);

        assertEquals(expectedUser, actualResult.get());

    }

    @Test
    public void getUser_Unsuccessfully_ReturnsNull(){
        when(userRepositoryMock.findUserById(1)).thenReturn(null);

        Optional<User> actualResult = userManager.getUser(1);

        verify(userRepositoryMock).findUserById(1);

        assertEquals(Optional.empty(), actualResult);
    }

}
