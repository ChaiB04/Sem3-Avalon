package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.controller.converters.UserConverter;
import individual.individualsem3backend.controller.dtos.user.CreateUserRequest;
import individual.individualsem3backend.controller.dtos.user.CreateUserResponse;
import individual.individualsem3backend.controller.dtos.user.UpdateUserRequest;
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
public class UserController {
    private UserManager userManagerUseCase;
    private UserConverter converter;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {

        User user = converter.createUserRequestConvertToUser(request);

        CreateUserResponse response = converter.userConvertToCreateUserResponse(userManagerUseCase.createUser(user));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //@RolesAllowed({"ADMINISTRATOR"})
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

    @RolesAllowed({"CUSTOMER", "ADMINISTRATOR"})
    @PutMapping("{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Integer userId,
                                              @RequestBody UpdateUserRequest request)
    {
        request.setId(userId);
        userManagerUseCase.editUser(converter.updateUserRequestConvertToUser(request));

        return ResponseEntity.noContent().build();
    }
}
