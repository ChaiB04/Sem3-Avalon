package individual.individualsem3backend.controller.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    private String description;
    private String color;
    private String base64picture;

}
