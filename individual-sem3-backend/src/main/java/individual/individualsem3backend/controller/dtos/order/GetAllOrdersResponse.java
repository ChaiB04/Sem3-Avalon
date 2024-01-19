package individual.individualsem3backend.controller.dtos.order;

import individual.individualsem3backend.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrdersResponse {
    private List<Order> allOrders;
}
