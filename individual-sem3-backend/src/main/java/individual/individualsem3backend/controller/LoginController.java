package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.controller.converters.LoginConverter;
import individual.individualsem3backend.controller.dtos.user.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginManager loginManager;
    private LoginConverter converter;


    @PostMapping()
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest request){
        UserLoginResponse user = converter.userConvertedToUserLoginResponse(loginManager.userLogin(request.getEmail(), request.getPassword()));
        return ResponseEntity.ok().body(user);
    }
}
