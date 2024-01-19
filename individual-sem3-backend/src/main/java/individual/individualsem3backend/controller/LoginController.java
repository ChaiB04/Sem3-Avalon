package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.controller.converters.LoginConverter;
import individual.individualsem3backend.controller.dtos.user.UserLoginRequest;
import individual.individualsem3backend.controller.dtos.user.UserLoginResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final LoginManager loginManager;
    private final LoginConverter converter;

    @PostMapping()
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginRequest request){
        UserLoginResponse user = converter.userConvertedToUserLoginResponse(loginManager.userLogin(request.getEmail(), request.getPassword()));
        return ResponseEntity.ok().body(user);
    }


}