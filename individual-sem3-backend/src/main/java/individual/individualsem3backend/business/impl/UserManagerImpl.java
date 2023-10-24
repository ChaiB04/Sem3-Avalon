package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.domain.enumerations.Role;
import individual.individualsem3backend.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserManagerImpl implements UserManager {
        private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User createUser(User newUser) {
        if(newUser != null){
            newUser.setRole(Role.Customer);
            String encodedPassword = passwordEncoder.encode(newUser.getPassword());

            newUser.setPassword(encodedPassword);

            return userRepository.save(newUser);
        }
        else{
            throw new UserException("Could not create user.");
        }
    }
    @Override
    public Optional<User> getUser(int userId) {
        if(userId > -1){
            return Optional.ofNullable(userRepository.findUserById(userId));
        }
        else{
            throw new UserException("Could not find user id.");
        }
    }

    public void deleteUser(int userid){
        if(userid > -1){
            userRepository.deleteById(userid);
        }
        else{
            throw new UserException("Could not find user id.");
        }
    }

    public void editUser(User editedUser){
        try{
            if(editedUser != null){
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
            else{
                throw new UserException("No user available to update to.");
            }
        }
        catch(Exception ex){
            throw new UserException("Could not edit the user.");
        }

    }
}
