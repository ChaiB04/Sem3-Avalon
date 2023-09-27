package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {

    private static Integer NEXT_ID = 1;

    private final List<User> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return this.savedUsers
                .stream()
                .filter(user -> user.getEmail() == email && user.getPassword() == password)
                .findFirst()
                .orElse(null);
    }

    public User findByEmail(String email){
        return this.savedUsers.stream().filter(user -> user.getEmail() == email).findFirst()
                .orElse(null);
    }

    @Override
    public void update(User user){
        savedUsers.set(user.getId() -1 , user);
    }

    @Override
    public User save(User user) {
        user.setId(NEXT_ID);
        NEXT_ID++;
        this.savedUsers.add(user);
        return user;
    }

    @Override
    public void deleteById(Integer userId) {
        this.savedUsers.removeIf(user -> user.getId() == userId);
    }
}
