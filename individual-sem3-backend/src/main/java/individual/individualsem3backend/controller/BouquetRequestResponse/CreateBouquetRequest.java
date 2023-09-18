package individual.individualsem3backend.controller.BouquetRequestResponse;

import individual.individualsem3backend.domain.Flower;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBouquetRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private Double price;
    private String description;
    private List<Flower> flowers;
    private String colorOfBow;
}
