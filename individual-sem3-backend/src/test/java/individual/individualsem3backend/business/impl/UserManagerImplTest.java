package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void createUser_WithNoInputReturnsUserException_With404BadRequest(){
        Throwable thrown = catchThrowable(() -> userManager.createUser(null));
        assertThat(thrown).isInstanceOf(UserException.class).hasMessage("400 BAD_REQUEST \"Could not create user.\"");
    }
    @Test
    public void createUser_WithNoInput_ThrowsUserException() {
        assertThrows(UserException.class, () -> userManager.createUser(null));
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
    public void userLogsIn_WithNoInput_ReturnsUserException(){
        assertThrows(UserException.class, () -> userManager.userLogin(null, null));
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
    public void getUser_WithNegativeInput_ReturnsUserException(){
        assertThrows(UserException.class, () -> userManager.getUser(-1));
    }

    @Test
    public void getUser_Unsuccessfully_ReturnsNull(){
        when(userRepositoryMock.findUserById(1)).thenReturn(null);

        Optional<User> actualResult = userManager.getUser(1);

        verify(userRepositoryMock).findUserById(1);

        assertEquals(Optional.empty(), actualResult);
    }

    @Test
    public void deleteUser_Successfully(){
        User user = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();


        userManager.deleteUser(user.getId());

        verify(userRepositoryMock).deleteById(user.getId());
    }

    @Test
    public void deleteUser_ReturnsUserException_WithNegativeInput(){
        assertThrows(UserException.class, () -> userManager.deleteUser(-1));
    }

    @Test
    public void editUser_Successfully(){
        User user1 = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        User user2 = User.builder().id(1).firstname("Chai").lastname("Chai")
                .email("Chai@gmail.com").password("Chai").country("Me")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.findUserById(1)).thenReturn(user1);

        userManager.editUser(user2);

        verify(userRepositoryMock).findUserById(1);
        verify(userRepositoryMock).update(user2);

    }

    @Test
    public void editUser_WithNoUserParameter_ReturnsUserException(){
        assertThrows(UserException.class, () -> userManager.editUser(null));
    }

}
