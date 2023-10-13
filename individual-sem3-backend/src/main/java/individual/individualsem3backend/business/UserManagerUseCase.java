package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.User;

import java.util.Optional;

public interface UserManagerUseCase {

    void deleteUser(int userId);
    User createUser(User newUser);

     void editUser(User editedUser);
    Optional<User> getUser(int userId);
    User userLogin(String email, String password);

}
