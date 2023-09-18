package individual.individualsem3backend.controller.DecorationRequestResponse;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDecorationRequest {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    private String description;
    private int warranty;
    private String category;
}
