package individual.individualsem3backend.controller.FlowerRequestResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowerRequest {

    //Notnull got a big error idk why
    private Integer id;
    @NotBlank
    private String name;
    private Double price;
    private String description;
    @NotBlank
    private String color;
    private Integer lifeExpectancy;
}
