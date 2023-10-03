package individual.individualsem3backend.controller.OrderRequestRespone;
import individual.individualsem3backend.domain.Order;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrdersResponse {
    private List<Order> allOrders;
}
