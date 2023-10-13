package individual.individualsem3backend.controller.UserRequestResponse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginGetUserResponse {
    @NotNull
    private Integer id;
//    @NotBlank
//    @Email(message = "Invalid email address")
    private String email;
    private String accessToken;
}
