package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.business.converters.UserEntityConverter;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.domain.enumeration.Role;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.UserEntity;
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
        try{
            if(newUser != null){
                newUser.setRole(Role.CUSTOMER);
                String encodedPassword = passwordEncoder.encode(newUser.getPassword());

                newUser.setPassword(encodedPassword);

                return UserEntityConverter.userEntityConvertedToUser(userRepository.save(UserEntityConverter.userConvertedToUserEntity(newUser)));
            }
            else{
                throw new UserException("Could not create user.");
            }
        }
        catch(Exception ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    @Override
    public User getUser(int userId) {
        try{
            UserEntity userEntity = userRepository.findById(userId).get();

            if(userEntity != null){
                return UserEntityConverter.userEntityConvertedToUser(userEntity);
            }
            else{
                throw new UserException("Could not find user id.");
            }
        }
        catch(Exception ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public void deleteUser(int userid){
        try{
            if(userid > -1){
                userRepository.deleteById(userid);
            }
            else{
                throw new UserException("Could not find user id.");
            }
        }
        catch(Exception ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public void editUser(User editedUser){
        try{
            if(editedUser != null){
                //get user from database
                User user = UserEntityConverter.userEntityConvertedToUser( userRepository.findById(editedUser.getId()).get());

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
                userRepository.save(UserEntityConverter.userConvertedToUserEntity(user));
            }
            else{
                throw new UserException("No user available to update to.");
            }
        }
        catch(Exception ex){
            throw new UserException(ex.getMessage());
        }

    }
}
