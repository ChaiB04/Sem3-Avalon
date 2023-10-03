package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.User;

public interface UserRepository {

    void update(User user);
    User findById(int userID);
    User findByEmail(String productId);
    void deleteById(int userId);

    User findUserById(int userId);

    User findByEmailAndPassword(String email, String password);

    User save(User user);

}
