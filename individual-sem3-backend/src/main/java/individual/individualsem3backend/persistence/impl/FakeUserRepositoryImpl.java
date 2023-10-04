package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {

    private static int NEXT_ID = 1;

    private List<User> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    public void deleteById(int userId) {
        this.savedUsers.removeIf(user -> user.getId() == userId);
    }

    public User findUserById(int userId){
        return this.savedUsers
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);
    }
    public void update(User user){
        savedUsers.set(user.getId() -1 , user);

    }
    public User findByEmail(String email) {
        return this.savedUsers
                .stream()
                .filter(user -> user.getEmail() == email)
                .findFirst()
                .orElse(null);
    }

    public User findByEmailAndPassword(String email, String password){
        return this.savedUsers
                .stream()
                .filter(user -> user.getEmail() == email && user.getPassword() == password)
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        user.setId(NEXT_ID);
        NEXT_ID++;
        this.savedUsers.add(user);
        return user;
    }
}
