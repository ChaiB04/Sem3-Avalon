package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OrderManagerUseCase;
import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderManagerImpl implements OrderManagerUseCase {

    private OrderRepository orderRepository;

    public List<Order> getAllOrders(Integer userId){
        return orderRepository.findAll(userId);
    }

    public Optional<Order> findOrderById(Integer orderId){
        return Optional.ofNullable(orderRepository.findOrderById(orderId));
    }
    public Order create(Order neworder){
        Order order = Order.builder().userId(neworder.getUserId()).products(neworder.getProducts())
                .bundle_or_not(neworder.isBundle_or_not())
                .date_of_purchase(neworder.getDate_of_purchase()).build();

        return orderRepository.save(order);
    }

    public void delete(Integer orderId){
        orderRepository.deleteById(orderId);
    }

    public Order update(Order updatedOrder){
        Order oldOrder = orderRepository.findOrderById(updatedOrder.getId());

        oldOrder.setProducts(updatedOrder.getProducts());
        oldOrder.setBundle_or_not(updatedOrder.isBundle_or_not());
        oldOrder.setDate_of_purchase(updatedOrder.getDate_of_purchase());

        orderRepository.update(oldOrder);

        return oldOrder;
    }
}
