package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OrderManager;
import individual.individualsem3backend.business.converters.OrderEntityConverter;
import individual.individualsem3backend.business.exception.OrderException;
import individual.individualsem3backend.controller.converters.OrderConverter;
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
    private OrderEntityConverter converter;

    public List<Order> getAllOrders(Integer userId){
        if(userId > -1){

            return converter.listOfOrderEntityConvertedToListOfOrder(orderRepository.findByUserId(userId));
        }
        else{
            throw new OrderException("Cannot find orders with a negative user id.");
        }
    }

    public Order findOrderById(Integer orderId){
        if(orderId > -1){
            return OrderEntityConverter.orderEntityConvertedToOrder(orderRepository.findById(orderId).get());
        }
        else{
            throw new OrderException("Cannot find orders with a negative user id.");
        }
    }
    public Order create(Order neworder){

        if(neworder != null){
            Order order = Order.builder().userId(neworder.getUserId()).products(neworder.getProducts())
                    .isBundled(neworder.isBundled())
                    .dateOfPurchase(neworder.getDateOfPurchase()).build();

            return OrderEntityConverter.orderEntityConvertedToOrder(orderRepository.save(converter.orderConvertedToOrderEntity(order)));
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
                Order oldOrder = OrderEntityConverter.orderEntityConvertedToOrder(orderRepository.findById(updatedOrder.getId()).get());

                if(oldOrder != null){

                    oldOrder.setProducts(updatedOrder.getProducts());
                    oldOrder.setBundled(updatedOrder.isBundled());
                    oldOrder.setDateOfPurchase(updatedOrder.getDateOfPurchase());

                    orderRepository.save(converter.orderConvertedToOrderEntity(oldOrder));

                    return oldOrder;
                }
            }
            else{
                throw new OrderException("Something went wrong while updating the order.");
            }
        }
        catch(Exception ex){
            throw new OrderException("Something went wrong while updating the order.");
        }
        return null;
    }
}
