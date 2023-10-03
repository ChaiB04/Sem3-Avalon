package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.UserManagerUseCase;
import individual.individualsem3backend.controller.Converters.UserConverter;
import individual.individualsem3backend.controller.UserRequestResponse.*;
import individual.individualsem3backend.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserManagerUseCase userManagerUseCase;
    private UserConverter converter;


    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {

        User user = converter.createUserRequestConvertToUser(request);

        CreateUserResponse response = converter.userConvertToCreateUserResponse(userManagerUseCase.createUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userManagerUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
        final Optional<User> UserOptional = userManagerUseCase.getUser(userId);
        if (UserOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(UserOptional.get());
    }

    @PutMapping("{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Integer userId,
                                              @RequestBody UpdateUserRequest request)
    {
        request.setId(userId);
        userManagerUseCase.editUser(converter.updateUserRequestConvertToUser(request));

        return ResponseEntity.noContent().build();
    }
}
