package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.controller.converters.UserConverter;
import individual.individualsem3backend.controller.dtos.user.UserLoginResponse;
import individual.individualsem3backend.controller.dtos.user.UserLoginRequest;
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
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest request){
        UserLoginResponse user = loginManager.userLogin(request.getEmail(), request.getPassword());

        return ResponseEntity.ok().body(user);
    }
}
