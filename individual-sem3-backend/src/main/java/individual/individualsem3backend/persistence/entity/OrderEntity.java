package individual.individualsem3backend.persistence.entity;

import individual.individualsem3backend.domain.Product;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotBlank
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_bundled")
    private boolean isBundled;

    @OneToMany
    private List<ProductEntity> products;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;
}
