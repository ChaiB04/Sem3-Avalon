package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByUserId(Integer id);

}
