package individual.individualsem3backend.controller.OrderRequestRespone;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrdersRequest {
    private Integer userId;
}
