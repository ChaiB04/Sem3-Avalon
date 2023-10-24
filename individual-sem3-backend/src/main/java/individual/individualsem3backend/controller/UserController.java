package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.controller.Converters.UserConverter;
import individual.individualsem3backend.controller.UserRequestResponse.*;
import individual.individualsem3backend.domain.User;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
//@CrossOrigin("http://localhost:5173/")
public class UserController {
    private UserManager userManagerUseCase;
    private UserConverter converter;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {

        User user = converter.createUserRequestConvertToUser(request);

        CreateUserResponse response = converter.userConvertToCreateUserResponse(userManagerUseCase.createUser(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"Administrator"})
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userManagerUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
        final Optional<User> UserOptional = userManagerUseCase.getUser(userId);
        return UserOptional.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RolesAllowed({"Customer", "Administrator"})
    @PutMapping("{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Integer userId,
                                              @RequestBody UpdateUserRequest request)
    {
        request.setId(userId);
        userManagerUseCase.editUser(converter.updateUserRequestConvertToUser(request));

        return ResponseEntity.noContent().build();
    }
}
