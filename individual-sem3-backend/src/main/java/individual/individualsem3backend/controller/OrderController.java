package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.OrderManager;
import individual.individualsem3backend.controller.converters.OrderConverter;
import individual.individualsem3backend.controller.dtos.order.CreateOrderRequest;
import individual.individualsem3backend.controller.dtos.order.CreateOrderResponse;
import individual.individualsem3backend.controller.dtos.order.GetAllOrdersRequest;
import individual.individualsem3backend.controller.dtos.order.GetAllOrdersResponse;
import individual.individualsem3backend.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderManager orderManagerUseCase;

    private OrderConverter converter;

    @GetMapping("oneUser/{userId}")
    public ResponseEntity<GetAllOrdersResponse> getAllOrders(@PathVariable Integer userId) {
        GetAllOrdersRequest request = GetAllOrdersRequest.builder().userId(userId).build();

        GetAllOrdersResponse response = converter.orderListConvertToGetAllOrderResponse(
                        orderManagerUseCase.getAllOrders(converter.getAllOrderRequestConvertToUserId(request))
                        );

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {

        Order order = converter.createOrderRequestConvertToOrder(request);

        CreateOrderResponse response = converter.orderConvertToCreateOrderResponse(orderManagerUseCase.create(order));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer orderId){
        Order order = orderManagerUseCase.findOrderById(orderId);
        return ResponseEntity.ok().body(order);
    }
}
