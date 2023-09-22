package individual.individualsem3backend.controller.FlowerRequestResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlowerRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    private String description;
    @NotBlank
    private String color;
    @NotNull
    private Integer lifeExpectancy;
}
