package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OrderManager;
import individual.individualsem3backend.business.converters.OrderEntityConverter;
import individual.individualsem3backend.business.exception.OrderException;
import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.OrderRepository;
import individual.individualsem3backend.persistence.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderManagerImpl implements OrderManager {

    private OrderRepository orderRepository;

    public List<Order> getAllOrders(Integer userId){
        try{
            if(userId > -1){

                return OrderEntityConverter.listOfOrderEntityConvertedToListOfOrder(orderRepository.findByUserId(userId));
            }
            else{
                throw new OrderException("Cannot find orders with a negative user id.");
            }
        }
        catch(Exception ex){
            throw new OrderException(ex.getMessage());
        }
    }

    public Order findOrderById(Integer orderId){
        try{
            if(orderId > -1){
                return OrderEntityConverter.orderEntityConvertedToOrder(orderRepository.findById(orderId).get());
            }
            else{
                throw new OrderException("Cannot find orders with a negative user id.");
            }
        }
        catch(Exception ex){
            throw new OrderException(ex.getMessage());
        }
    }
    public Order create(Order neworder){
        try{
            if(neworder != null){
                Order order = Order.builder().userId(neworder.getUserId()).products(neworder.getProducts())
                        .isBundled(neworder.isBundled())
                        .dateOfPurchase(neworder.getDateOfPurchase()).build();

                return OrderEntityConverter.orderEntityConvertedToOrder(orderRepository.save(OrderEntityConverter.orderConvertedToOrderEntity(order)));
            }
            else{
                throw new OrderException("Something went wrong while creating the order.");
            }
        }
        catch(Exception ex){
            throw new OrderException(ex.getMessage());
        }
    }

    public void delete(Integer orderId){
        try{
            if(orderId > -1){
                orderRepository.deleteById(orderId);
            }
            else{
                throw new OrderException("Cannot delete order with a negative id.");
            }
        }
        catch(Exception ex){
            throw new OrderException(ex.getMessage());
        }
    }

    public Order update(Order updatedOrder){
        try{
            if(updatedOrder.getId() > -1){
                OrderEntity orderEntity = orderRepository.findById(updatedOrder.getId()).get();

                if(orderEntity != null){

                    Order oldOrder = OrderEntityConverter.orderEntityConvertedToOrder(orderEntity);

                    oldOrder.setProducts(updatedOrder.getProducts());
                    oldOrder.setBundled(updatedOrder.isBundled());
                    oldOrder.setDateOfPurchase(updatedOrder.getDateOfPurchase());

                    orderRepository.save(OrderEntityConverter.orderConvertedToOrderEntity(oldOrder));

                    return oldOrder;
                }
            }
            else{
                throw new OrderException("Something went wrong while updating the order.");
            }
        }
        catch(Exception ex){
            throw new OrderException(ex.getMessage());
        }
        return null;
    }
}
