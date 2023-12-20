package individual.individualsem3backend.repository;

import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void saveUser_Successful() {
        UserEntity sampleUser = UserEntity.builder().id(1).firstname("Neuvillette").lastname("Dragonidk")
                .email("Neuvi@gmail.com").password("idontlikefurina").country("Fontaine")
                .city("Court of Fontaine").housenumber(69).street("Courthouse").zipcode("4829HF").phonenumber("9039032").build();

        userRepository.save(sampleUser);

        Optional<UserEntity> userEntityOptional = userRepository.findById(sampleUser.getId());

        assertTrue(userEntityOptional.isPresent());

        UserEntity retrievedUserEntity = userEntityOptional.get();

        assertEquals(sampleUser.getId(), retrievedUserEntity.getId());
    }


    @Test
    void getUser_Successful() {
        UserEntity sampleUser = UserEntity.builder()
                .id(2)
                .firstname("Neuvillette")
                .lastname("Dragonidk")
                .email("Neuvi@gmail.com")
                .password("idontlikefurina")
                .country("Fontaine")
                .city("Court of Fontaine")
                .housenumber(69)
                .street("Courthouse")
                .zipcode("4829HF")
                .phonenumber("9039032")
                .build();

        userRepository.save(sampleUser);

        Optional<UserEntity> entity = userRepository.findById(2);

        assertTrue(entity.isPresent());

        UserEntity retrievedUserEntity = entity.get();

        assertEquals(sampleUser.getId(), retrievedUserEntity.getId());
        assertEquals(sampleUser.getFirstname(), retrievedUserEntity.getFirstname());
        assertEquals(sampleUser.getLastname(), retrievedUserEntity.getLastname());
        assertEquals(sampleUser.getEmail(), retrievedUserEntity.getEmail());
    }

}
