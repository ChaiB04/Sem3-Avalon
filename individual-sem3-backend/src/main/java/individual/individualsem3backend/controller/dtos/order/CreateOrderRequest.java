package individual.individualsem3backend.controller.dtos.order;

import individual.individualsem3backend.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private Integer userId;
    private List<Product> products;
    private boolean bundle_or_not;
    private Date date_of_purchase;
}
