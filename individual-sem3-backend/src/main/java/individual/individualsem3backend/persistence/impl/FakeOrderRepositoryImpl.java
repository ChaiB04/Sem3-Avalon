package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeOrderRepositoryImpl implements OrderRepository {
    private static int NEXT_ID = 1;

    private List<Order> savedOrders;

    public FakeOrderRepositoryImpl() {
        this.savedOrders = new ArrayList<>();
    }


    @Override
    public Order findOrderById(Integer orderId) {
        return this.savedOrders
                .stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Order order){
        savedOrders.set(order.getId() -1 , order);
    }

    @Override
    public Order save(Order order) {
        order.setId(NEXT_ID);
        NEXT_ID++;
        this.savedOrders.add(order);
        return order;
    }

    @Override
    public List<Order> findAll(Integer userId) {
        return this.savedOrders
                .stream()
                .filter(order -> order.getUserId().equals(userId))
                .toList();
    }

    @Override
    public void deleteById(Integer orderId) {
        this.savedOrders.removeIf(order ->order.getId().equals(orderId));
    }
}
