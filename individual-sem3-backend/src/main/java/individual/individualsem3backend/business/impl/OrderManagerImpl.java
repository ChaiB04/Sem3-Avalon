package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OrderManager;
import individual.individualsem3backend.business.exception.OrderException;
import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderManagerImpl implements OrderManager {

    private OrderRepository orderRepository;

    public List<Order> getAllOrders(Integer userId){
        if(userId > -1){
            return orderRepository.findAll(userId);
        }
        else{
            throw new OrderException("Cannot find orders with a negative user id.");
        }
    }

    public Optional<Order> findOrderById(Integer orderId){
        if(orderId > -1){
            return Optional.ofNullable(orderRepository.findOrderById(orderId));
        }
        else{
            throw new OrderException("Cannot find orders with a negative user id.");
        }
    }
    public Order create(Order neworder){

        if(neworder != null){
            Order order = Order.builder().userId(neworder.getUserId()).products(neworder.getProducts())
                    .bundle_or_not(neworder.isBundle_or_not())
                    .date_of_purchase(neworder.getDate_of_purchase()).build();

            return orderRepository.save(order);
        }
        else{
            throw new OrderException("Something went wrong while creating the order.");
        }
    }

    public void delete(Integer orderId){
        if(orderId > -1){
            orderRepository.deleteById(orderId);
        }
        else{
            throw new OrderException("Cannot delete order with a negative id.");
        }
    }

    public Order update(Order updatedOrder){
        try{
            if(updatedOrder.getId() > -1){
                Order oldOrder = orderRepository.findOrderById(updatedOrder.getId());

                oldOrder.setProducts(updatedOrder.getProducts());
                oldOrder.setBundle_or_not(updatedOrder.isBundle_or_not());
                oldOrder.setDate_of_purchase(updatedOrder.getDate_of_purchase());

                orderRepository.update(oldOrder);

                return oldOrder;
            }
            else{
                throw new OrderException("Something went wrong while updating the order.");
            }
        }
        catch(Exception ex){
            throw new OrderException("Something went wrong while updating the order.");
        }
    }
}
