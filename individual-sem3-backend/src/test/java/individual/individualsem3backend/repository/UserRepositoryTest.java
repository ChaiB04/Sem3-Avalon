package individual.individualsem3backend.repository;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserManager manager;

    @Autowired
    private UserRepository repository;

//    @Test
//    void saveUserWithAllFields()
//    {
//        UserEntity userEntity = UserEntity.builder()
//                .id(1)
//                .firstname("Neuvillette")
//                .lastname("Dragonidk")
//                .email("Neuvi@gmail.com")
//                .password("idontlikefurina")
//                .country("Fontaine")
//                .city("Court of Fontaine")
//                .housenumber(69)
//                .street("Courthouse")
//                .zipcode("4829HF")
//                .phonenumber("9039032")
//                .build();
//
//        //UserEntity user
//    }

}
