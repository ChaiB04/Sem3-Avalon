package individual.individualsem3backend.controller.BouquetRequestResponse;

import individual.individualsem3backend.domain.Bouquet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBouquetResponse {
    private List<Bouquet> allBouquets;
}
