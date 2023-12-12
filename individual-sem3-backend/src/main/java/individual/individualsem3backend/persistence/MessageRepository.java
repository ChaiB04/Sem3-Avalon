package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MessageRepository  extends JpaRepository<MessageEntity, Integer> {
//    List<MessageEntity> findAllByChat_id(Integer chat_id);
}
