package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.controller.converters.LoginConverter;
import individual.individualsem3backend.controller.dtos.GetGoogleLink;
import individual.individualsem3backend.controller.dtos.user.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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