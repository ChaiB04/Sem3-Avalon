package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.GoogleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleUserRepository extends JpaRepository<GoogleUserEntity, Integer> {

    boolean existsBySub(String sub);
    GoogleUserEntity getGoogleUserEntityBySub(String sub);
}
