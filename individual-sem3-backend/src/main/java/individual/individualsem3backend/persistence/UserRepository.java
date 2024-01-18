package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM  MessageEntity m WHERE m.chat_id = (SELECT C.id FROM ChatEntity C WHERE C.user1 = :id)")
    void deleteChatMessages(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderEntity o WHERE o.userId = :id")
    void deleteOrders(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ChatEntity c WHERE c.user1 = :id")
    void deleteChats(Integer id);


}
