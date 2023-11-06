package individual.individualsem3backend.controller.converters;

import individual.individualsem3backend.controller.dtos.order.*;
import individual.individualsem3backend.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderConverter {
    public Order createOrderRequestConvertToOrder(CreateOrderRequest request){
        return Order.builder().userId(request.getUserId())
                .isBundled(request.isBundle_or_not()).products(request.getProducts())
                .dateOfPurchase(request.getDate_of_purchase()).build();
    }

    public CreateOrderResponse orderConvertToCreateOrderResponse(Order order){
        return CreateOrderResponse.builder()
                .id(order.getId()).build();
    }

    public Integer getAllOrderRequestConvertToUserId(GetAllOrdersRequest request){
        return request.getUserId();
    }

    public GetAllOrdersResponse orderListConvertToGetAllOrderResponse(List<Order> orderList){
        return GetAllOrdersResponse.builder().allOrders(orderList).build();
    }
}
