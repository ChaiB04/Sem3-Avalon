package individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    private int id;
    private String name;
    private Double price;
}
