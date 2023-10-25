package individual.individualsem3backend.controller.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    @NotNull
    private Integer id;
}
