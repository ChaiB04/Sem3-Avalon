package individual.individualsem3backend.persistence.entity;

import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Entity
@Table(name = "user_order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_bundled")
    private boolean isBundled;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<ProductEntity> products;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;
}
