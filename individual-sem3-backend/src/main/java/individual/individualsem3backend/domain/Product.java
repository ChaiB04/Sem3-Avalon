package individual.individualsem3backend.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
//@Builder
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    @NotBlank
    private String name;
    private Double price;
    private String description;
}
