package individual.individualsem3backend.controller.FlowerRequestResponse;

import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.domain.Product;
import lombok.*;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFlowerResponse {
    private List<Flower> allFlowers;
}
