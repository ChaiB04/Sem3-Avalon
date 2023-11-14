package individual.individualsem3backend.business.converters;

import individual.individualsem3backend.domain.Order;
import individual.individualsem3backend.persistence.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEntityConverter {
    private OrderEntityConverter(){}

    public static Order orderEntityConvertedToOrder(OrderEntity entity){
        return Order.builder().id(entity.getId()).userId(entity.getUserId())
                .products(ProductEntityConverter.listOfProductEntitiesConvertedToListOfProducts(entity.getProducts()))
                .dateOfPurchase(entity.getDateOfPurchase()).isBundled(entity.isBundled()).build();
    }

    public static OrderEntity orderConvertedToOrderEntity(Order order){
        return OrderEntity.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .dateOfPurchase(order.getDateOfPurchase())
                .isBundled(order.isBundled()).products(ProductEntityConverter.listOfProductConvertedToListOfProductEntities(order.getProducts()))
                .build();
    }

    public static List<Order> listOfOrderEntityConvertedToListOfOrder(List<OrderEntity> entities){
        return entities.stream().map(OrderEntityConverter::orderEntityConvertedToOrder).toList();
    }
}
