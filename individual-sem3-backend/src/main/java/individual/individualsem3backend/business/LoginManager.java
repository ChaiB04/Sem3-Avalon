package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.User;

public interface LoginManager {
    String userLogin(String email, String password);
    String generateAccessToken(User user);
}
