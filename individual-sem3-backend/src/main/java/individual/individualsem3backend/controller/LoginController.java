package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.controller.Converters.UserConverter;
import individual.individualsem3backend.controller.UserRequestResponse.UserLoginGetUserResponse;
import individual.individualsem3backend.controller.UserRequestResponse.UserLoginRequest;
import individual.individualsem3backend.domain.User;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginManager loginManager;
    private UserConverter converter;

    @PostMapping()
    public ResponseEntity<UserLoginGetUserResponse> LoginUser(@RequestBody UserLoginRequest request){

        UserLoginGetUserResponse user = loginManager.userLogin(request.getEmail(), request.getPassword());

        //UserLoginGetUserResponse response = converter.userConvertToUserLoginGetUserResponse(user);

        return ResponseEntity.ok().body(user);
    }
}
