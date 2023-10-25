package individual.individualsem3backend.controller.dtos.product;

import individual.individualsem3backend.domain.Product;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private List<Product> allProducts;

}
