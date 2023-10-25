package individual.individualsem3backend.controller.dtos.order;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrdersRequest {
    private Integer userId;
}
