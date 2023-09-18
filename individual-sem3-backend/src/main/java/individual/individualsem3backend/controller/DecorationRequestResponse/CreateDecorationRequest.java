package individual.individualsem3backend.controller.DecorationRequestResponse;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDecorationRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private Double price;
    private String description;
    private int warranty;
    private String category;
}
