package individual.individualsem3backend.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @NotNull
    private Integer id;
    @NotNull
    private Integer userId;
    private List<Product> products;
    private boolean bundle_or_not;
    private Date date_of_purchase;
}
