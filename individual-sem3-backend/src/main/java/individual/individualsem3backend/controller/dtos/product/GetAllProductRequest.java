package individual.individualsem3backend.controller.dtos.product;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductRequest {
    private String name;
    private String color;
    private Double price;
}
