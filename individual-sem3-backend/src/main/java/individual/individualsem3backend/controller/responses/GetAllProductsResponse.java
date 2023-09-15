package individual.individualsem3backend.controller.responses;

import individual.individualsem3backend.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private List<Product> allProducts;
}
