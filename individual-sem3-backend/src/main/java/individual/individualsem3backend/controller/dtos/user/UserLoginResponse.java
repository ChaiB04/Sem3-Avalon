package individual.individualsem3backend.controller.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    @NotBlank
    private String accessToken;
}
