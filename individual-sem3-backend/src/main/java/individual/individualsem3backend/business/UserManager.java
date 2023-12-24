package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.User;

public interface UserManager {
    boolean linkGoogleToAccount(Integer userId, String accessTokenGoogle);

    void deleteUser(int userId);
    User createUser(User newUser);

    void editUser(User editedUser);
    User getUser(int userId);

}
