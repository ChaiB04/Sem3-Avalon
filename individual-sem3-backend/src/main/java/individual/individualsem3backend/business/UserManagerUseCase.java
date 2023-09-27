package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.User;

public interface UserManagerUseCase {

    public User userLogin(String email, String password);

    public User createUser(User newUser) throws Exception;

    public void deleteUser(User user);

    public void editUser(User editedUser);
}
