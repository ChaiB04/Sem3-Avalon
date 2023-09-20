package individual.individualsem3backend.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Decoraction extends Product{
    private Integer warranty;
    private String category;
}
