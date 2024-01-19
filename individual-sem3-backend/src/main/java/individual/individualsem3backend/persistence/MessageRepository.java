package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<MessageEntity, Integer> {
}
