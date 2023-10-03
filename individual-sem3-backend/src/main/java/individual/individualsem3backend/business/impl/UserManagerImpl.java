package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.UserManagerUseCase;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManagerUseCase {
        private UserRepository userRepository;

    public User userLogin(String email, String password){
        if(!email.isEmpty() && !password.isEmpty()){
          return userRepository.findByEmailAndPassword(email, password);
        }
        return null;
    }

    public User createUser(User newUser) {

        return userRepository.save(newUser);
    }
    @Override
    public Optional<User> getUser(int userId) {
        return Optional.ofNullable(userRepository.findById(userId));
    }

    public void deleteUser(int userid){
        userRepository.deleteById(userid);
    }

    public void editUser(User editedUser){
        //get user from database
        User user = userRepository.findUserById(editedUser.getId());

        //change the information
        user.setEmail(editedUser.getEmail());
        user.setPassword(editedUser.getPassword());
        user.setCity(editedUser.getCity());
        user.setFirstname(editedUser.getFirstname());
        user.setLastname(editedUser.getLastname());
        user.setCountry(editedUser.getCountry());
        user.setStreet(editedUser.getStreet());
        user.setHousenumber(editedUser.getHousenumber());
        user.setZipcode(editedUser.getZipcode());
        user.setPhonenumber(editedUser.getPhonenumber());

        //save it again
        userRepository.update(user);
    }
}
