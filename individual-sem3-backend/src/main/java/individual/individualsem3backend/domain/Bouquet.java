package individual.individualsem3backend.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor
public class Bouquet extends Product{

    private List<Flower> flowers;
    private String colorOfBow;
}
