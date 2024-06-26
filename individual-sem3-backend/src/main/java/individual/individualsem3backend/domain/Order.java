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
    private Integer id;
    @NotNull
    private Integer userId;
    private List<Product> products;
    private boolean isBundled;
    private Date dateOfPurchase;
}
