package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.ChatEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository  extends JpaRepository<ChatEntity, Integer> {

//    @Query("SELECT ch.from, ch.to " +
//            "FROM ChatEntity ch " +
//            "WHERE ch.to = :receiver " +
//            "AND ch.from = ")
//    List<ChatEntity> findLatestChatsForTo(@Param("receiver") Integer receiver);

//    ChatEntity findByChat_id(Integer id);

//      @Query("SELECT ch.id, ch.user1, ch.user2, ch.messages FROM ChatEntity ch " +
//              "WHERE ch.user1 = :id OR ch.user2 = :id")
//      List<ChatEntity> findAllChats(@Param("id") Integer user1);
//      List<ChatEntity> findAllByUser1OrUser2(Integer userId, Integer userid);
      List<ChatEntity> findAllByUser1(Integer user1);
      List<ChatEntity> findAllByUser2(Integer user2);
}
