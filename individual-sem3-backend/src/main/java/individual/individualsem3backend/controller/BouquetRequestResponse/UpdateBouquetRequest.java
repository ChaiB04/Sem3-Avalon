package individual.individualsem3backend.controller.BouquetRequestResponse;

import individual.individualsem3backend.domain.Flower;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBouquetRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private Double price;
    private String description;
    private List<Flower> flowers;
    private String colorOfBow;
}
