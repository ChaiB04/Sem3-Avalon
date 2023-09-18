package individual.individualsem3backend.controller.DecorationRequestResponse;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDecorationResponse {
    private Integer id;
}
