package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

//    @Query("SELECT * FROM app_user WHERE email = ?1 & password = ")
    UserEntity findByEmailAndPassword(String email, String password);

}
