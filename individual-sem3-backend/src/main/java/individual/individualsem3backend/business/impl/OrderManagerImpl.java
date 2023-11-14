package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.OrderManager;
import individual.individualsem3backend.business.converters.OrderEntityConverter;
import individual.individualsem3backend.business.exception.OrderException;
import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.OrderRepository;
import individual.individualsem3backend.persistence.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderManagerImpl implements OrderManager {

    private OrderRepository orderRepository;

    public List<Order> getAllOrders(Integer userId){
        try{
            return OrderEntityConverter.listOfOrderEntityConvertedToListOfOrder(orderRepository.findByUserId(userId));
        }
        catch(Exception ex){
            throw new OrderException(ex.getMessage());
        }
    }

    public Order findOrderById(Integer orderId) {
        try {
            Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);

            if (optionalOrderEntity.isPresent()) {
                return OrderEntityConverter.orderEntityConvertedToOrder(optionalOrderEntity.get());
            } else {
                throw new OrderException("Order not found for ID: " + orderId);
            }
        } catch (OrderException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new OrderException("Something went wrong while finding the order.");
        }
    }

    public Order create(Order neworder){
        try{
            if(neworder != null){
                OrderEntity entity = OrderEntityConverter.orderConvertedToOrderEntity(neworder);
                OrderEntity savedEntity = orderRepository.save(entity);
                return OrderEntityConverter.orderEntityConvertedToOrder(savedEntity);
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

    public Order update(Order updatedOrder) {
        try {
                Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(updatedOrder.getId());

                if (optionalOrderEntity.isPresent()) {
                    OrderEntity orderEntity = optionalOrderEntity.get();

                    Order oldOrder = OrderEntityConverter.orderEntityConvertedToOrder(orderEntity);

                    oldOrder.setProducts(updatedOrder.getProducts());
                    oldOrder.setBundled(updatedOrder.isBundled());
                    oldOrder.setDateOfPurchase(updatedOrder.getDateOfPurchase());

                    orderRepository.save(OrderEntityConverter.orderConvertedToOrderEntity(oldOrder));

                    return oldOrder;
                } else {
                    throw new OrderException("Order not found for ID: " + updatedOrder.getId());
                }
        } catch (OrderException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new OrderException("Something went wrong while updating the order.");
        }
    }

}
