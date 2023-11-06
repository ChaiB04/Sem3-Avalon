package individual.individualsem3backend.domain;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    private String description;
    @NotBlank
    private String color;
}
