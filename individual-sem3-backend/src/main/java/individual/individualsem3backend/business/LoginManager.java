package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.dtos.user.UserLoginResponse;

public interface LoginManager {
    UserLoginResponse userLogin(String email, String password);
}
