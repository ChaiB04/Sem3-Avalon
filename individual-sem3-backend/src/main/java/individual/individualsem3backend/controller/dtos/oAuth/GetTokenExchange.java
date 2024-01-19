package individual.individualsem3backend.controller.dtos.oAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTokenExchange {
    private String code;
    private boolean login;
}
