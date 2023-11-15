package individual.individualsem3backend.domain;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter {
    private String name;
    private Double price;
    private String color;
}
