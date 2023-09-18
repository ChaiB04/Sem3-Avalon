package individual.individualsem3backend.controller.responses;

import individual.individualsem3backend.domain.Bouquet;
import individual.individualsem3backend.domain.Decoraction;
import individual.individualsem3backend.domain.Flower;
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
    private List<Flower> allFlowers;
    private List<Decoraction> allDecorations;
    private List<Bouquet> allBouquets;

}
