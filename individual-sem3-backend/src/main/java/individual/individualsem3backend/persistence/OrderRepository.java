package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order findOrderById(Integer orderId);

    void update(Order order);

    Order save(Order order);

    List<Order> findAll(Integer userId);

    void deleteById(Integer orderId);
}
