package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.UserRequestResponse.UserLoginResponse;

public interface LoginManager {
    UserLoginResponse userLogin(String email, String password);
}
