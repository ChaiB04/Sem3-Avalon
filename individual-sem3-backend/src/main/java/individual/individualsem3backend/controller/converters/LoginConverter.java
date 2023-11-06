package individual.individualsem3backend.controller.converters;

import individual.individualsem3backend.controller.dtos.user.UserLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginConverter {

    public UserLoginResponse userConvertedToUserLoginResponse(String token){
        return UserLoginResponse.builder().accessToken(token).build();
    }
}
