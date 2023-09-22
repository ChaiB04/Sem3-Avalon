package individual.individualsem3backend.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Flower extends Product{
    private String color;
    private Integer lifeExpectancy;
}
