package individual.individualsem3backend.controller.dtos.oAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkGoogleAccount {
    private Integer user_id;
    private String accessToken;
}
