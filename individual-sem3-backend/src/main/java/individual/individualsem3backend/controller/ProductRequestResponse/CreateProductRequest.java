package individual.individualsem3backend.controller.ProductRequestResponse;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private int id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    private String description;
    private String color;
}
