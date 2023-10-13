package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.UserRequestResponse.UserLoginGetUserResponse;
import individual.individualsem3backend.domain.User;

public interface LoginManager {
    UserLoginGetUserResponse userLogin(String email, String password);
}
