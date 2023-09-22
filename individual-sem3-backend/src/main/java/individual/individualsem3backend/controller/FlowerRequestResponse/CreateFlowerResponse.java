package individual.individualsem3backend.controller.FlowerRequestResponse;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlowerResponse {
    @NotBlank
    private Integer id;
}
