package individual.individualsem3backend.controller.FlowerRequestResponse;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowerRequest {
    @NotBlank
    private Integer id;
    @NotBlank
    private String name;
    private Double price;
    private String description;
    @NotBlank
    private String color;
    @NotBlank
    private Integer lifeExpectancy;
}
