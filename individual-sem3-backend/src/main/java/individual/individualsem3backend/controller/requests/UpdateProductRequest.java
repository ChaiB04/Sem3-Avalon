package individual.individualsem3backend.controller.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Long countryId;
}
