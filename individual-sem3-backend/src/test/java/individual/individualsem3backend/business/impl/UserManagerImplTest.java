package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.domain.enumeration.Role;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserManagerImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserManagerImpl userManager;

    @Test
    void createAdminUser_Successfully_ReturnAdminUser(){

        User user = User.builder().firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")

                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        UserEntity expectedUserEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .role(Role.ADMINISTRATOR)
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();


        User expectedUser = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .role(Role.ADMINISTRATOR)
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(expectedUserEntity);

        User actualResult = userManager.createAdminUser(user);

        verify(userRepositoryMock).save(any(UserEntity.class));

        assertEquals(expectedUser, actualResult);
    }
    @Test
    void createUser_Successfully_ReturnUser(){

        User user = User.builder().firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        UserEntity expectedUserEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .role(Role.CUSTOMER)
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();


        User expectedUser = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .role(Role.CUSTOMER)
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(expectedUserEntity);

        User actualResult = userManager.createUser(user);

        verify(userRepositoryMock).save(any(UserEntity.class));

        assertEquals(expectedUser, actualResult);
    }

    @Test
    void createUser_WithNoInputReturnsUserException_With404BadRequest(){
        assertThrows(UserException.class, () -> userManager.createUser(null));
    }
    @Test
    void createUser_WithNoInput_ThrowsUserException() {
        assertThrows(UserException.class, () -> userManager.createUser(null));
    }


    @Test
    void getUser_Successfully_ReturnsUser(){
        UserEntity expectedUserEntity = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").role(Role.CUSTOMER).phonenumber("9039032").build();

        User expectedUser = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").role(Role.CUSTOMER).phonenumber("9039032").build();

        when(userRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(expectedUserEntity));

        User actualResult = userManager.getUser(1);

        verify(userRepositoryMock).findById(1);

        assertEquals(expectedUser, actualResult);

    }

    @Test
    void getUser_WithNegativeInput_ReturnsUserException(){
        assertThrows(UserException.class, () -> userManager.getUser(-1));
    }

    @Test
    void getUser_Unsuccessfully_ThrowsUserException(){
        assertThrows(UserException.class, () -> userManager.getUser(1));
    }

    @Test
    void deleteUser_Successfully(){
        User user = User.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();


        userManager.deleteUser(user.getId());

        verify(userRepositoryMock).deleteById(user.getId());
    }

//    @Test
//    public void deleteNotExistingUser(){
//        int userId = 123;
//        when(userRepositoryMock.deleteById(userId)).thenThrow(new EmptyResultDataAccessException(1));
//    }

    @Test
    void editUser_Successfully(){
        UserEntity userEntity1 = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        UserEntity userEntity2 = UserEntity.builder().id(1).firstname("Chai").lastname("Chai")
                .email("Chai@gmail.com").password("Chai").country("Me")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        User user2 = User.builder().id(1).firstname("Chai").lastname("Chai")
                .email("Chai@gmail.com").password("Chai").country("Me")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        when(userRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(userEntity1));

        userManager.editUser(user2);

        verify(userRepositoryMock).findById(1);
        verify(userRepositoryMock).save(userEntity2);

    }

    @Test
    void editUser_WithNoUserParameter_ReturnsUserException(){
        assertThrows(UserException.class, () -> userManager.editUser(null));
    }

}
