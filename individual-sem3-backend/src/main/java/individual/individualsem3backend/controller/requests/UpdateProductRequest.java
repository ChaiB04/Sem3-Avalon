package individual.individualsem3backend.controller.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    private String description;
}
