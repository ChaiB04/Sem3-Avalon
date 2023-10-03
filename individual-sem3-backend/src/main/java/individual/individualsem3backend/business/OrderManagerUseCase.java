package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderManagerUseCase {
    List<Order> getAllOrders(Integer userId);

    Optional<Order> findOrderById(Integer orderId);

    Order create(Order neworder);

    void delete(Integer orderId);

    Order update(Order updatedOrder);
}
