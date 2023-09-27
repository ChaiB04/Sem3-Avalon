package individual.individualsem3backend.persistence;

import individual.individualsem3backend.domain.User;

import java.util.List;

public interface UserRepository {
    public User findByEmailAndPassword(String email, String password);

    public void update(User user);
    public User save(User user);
    public void deleteById(Integer userId);
    public User findByEmail(String email);
}
