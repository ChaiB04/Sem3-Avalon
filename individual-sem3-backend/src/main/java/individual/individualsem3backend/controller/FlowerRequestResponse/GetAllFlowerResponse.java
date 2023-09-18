package individual.individualsem3backend.controller.FlowerRequestResponse;

import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFlowerResponse {
    private List<Flower> allFlowers;
}
