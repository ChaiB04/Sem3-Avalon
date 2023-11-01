package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

}
