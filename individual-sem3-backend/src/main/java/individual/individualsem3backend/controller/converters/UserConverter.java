package individual.individualsem3backend.controller.converters;

import individual.individualsem3backend.controller.dtos.user.CreateUserRequest;
import individual.individualsem3backend.controller.dtos.user.CreateUserResponse;
import individual.individualsem3backend.controller.dtos.user.UpdateUserRequest;
import individual.individualsem3backend.controller.dtos.user.UserLoginRequest;
import individual.individualsem3backend.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserConverter {
    public User createUserRequestConvertToUser(CreateUserRequest request){
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname()).country(request.getCountry()).city(request.getCity())
                .street(request.getStreet()).housenumber(request.getHousenumber()).zipcode(request.getZipcode())
                .phonenumber(request.getPhonenumber()).build();
    }

    public User loginUserRequestConvertToUser(UserLoginRequest request){
        return User.builder().email(request.getEmail()).password(request.getPassword()).build();
    }

    public CreateUserResponse userConvertToCreateUserResponse(User user){
        return CreateUserResponse.builder().id(user.getId()).build();
    }

    public User updateUserRequestConvertToUser(UpdateUserRequest request){
        return User.builder()
                .id(request.getId())
                .email(request.getEmail())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname()).country(request.getCountry()).city(request.getCity())
                .street(request.getStreet()).housenumber(request.getHousenumber()).zipcode(request.getZipcode())
                .phonenumber(request.getPhonenumber()).build();
    }


}
