package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository  extends JpaRepository<ChatEntity, Integer> {
      List<ChatEntity> findAllByUser1(Integer user1);
      List<ChatEntity> findAllByUser2(Integer user2);

      ChatEntity findByUser1(Integer user1);
      ChatEntity findByUser2(Integer user2);
}
