package individual.individualsem3backend.controller.UserRequestResponse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @NotBlank
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    private String password;

}
