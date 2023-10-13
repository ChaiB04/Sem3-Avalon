package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.OrderManager;
import individual.individualsem3backend.controller.Converters.OrderConverter;
import individual.individualsem3backend.controller.OrderRequestRespone.*;
import individual.individualsem3backend.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
//@CrossOrigin("http://localhost:5173/")
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

    @DeleteMapping("{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderManagerUseCase.delete(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer orderId){
        Optional<Order> orderOptional = orderManagerUseCase.findOrderById(orderId);
        if (orderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderOptional.get());
    }
}
