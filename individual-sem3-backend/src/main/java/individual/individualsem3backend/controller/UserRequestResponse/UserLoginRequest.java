package individual.individualsem3backend.controller.UserRequestResponse;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
