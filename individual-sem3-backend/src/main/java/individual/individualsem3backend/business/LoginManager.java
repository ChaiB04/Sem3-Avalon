package individual.individualsem3backend.business;

import individual.individualsem3backend.controller.dtos.user.UserLoginResponse;

public interface LoginManager {
    String userLogin(String email, String password);
}
