package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.business.converters.UserEntityConverter;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.domain.enumeration.Role;
import individual.individualsem3backend.external.GoogleApi;
import individual.individualsem3backend.persistence.GoogleUserRepository;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.GoogleUserEntity;
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
    private GoogleApi googleApi;
    private GoogleUserRepository googleUserRepository;

    @Override
    public User createAdminUser(User newUser) {
        try{
            if(newUser != null){
                newUser.setRole(Role.ADMINISTRATOR);
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
            throw new UserException("Something went wrong with creating a user");
        }
    }

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
            throw new UserException("Something went wrong with creating a user");
        }
    }

    @Override
    public User getUser(int userId) {
        try {
            Optional<UserEntity> userEntity = userRepository.findById(userId);

            if (userEntity.isPresent()) {
                return UserEntityConverter.userEntityConvertedToUser(userEntity.get());
            }
            else {
                throw new UserException("User not found for ID: " + userId);
            }
        } catch (UserException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UserException("Something went wrong while finding the user.");
        }
    }


    public void deleteUser(int userid){
        try{


            userRepository.deleteChatMessages(userid);
            userRepository.deleteOrders(userid);
            userRepository.deleteChats(userid);
            userRepository.deleteById(userid);
        }
        catch(Exception ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public void editUser(User editedUser) {
        try {
            if (editedUser != null) {
                Optional<UserEntity> optionalUserEntity = userRepository.findById(editedUser.getId());

                if (optionalUserEntity.isPresent()) {
                    User user = UserEntityConverter.userEntityConvertedToUser(optionalUserEntity.get());

                    user.setEmail(editedUser.getEmail());

                    if(editedUser.getPassword() != null && !editedUser.getPassword().isEmpty()){
                        user.setPassword(editedUser.getPassword());
                    }
                    user.setCity(editedUser.getCity());
                    user.setFirstname(editedUser.getFirstname());
                    user.setLastname(editedUser.getLastname());
                    user.setCountry(editedUser.getCountry());
                    user.setStreet(editedUser.getStreet());
                    user.setHousenumber(editedUser.getHousenumber());
                    user.setZipcode(editedUser.getZipcode());
                    user.setPicture(editedUser.getPicture());

                    userRepository.save(UserEntityConverter.userConvertedToUserEntity(user));
                } else {
                    throw new UserException("User not found for ID: " + editedUser.getId());
                }
            } else {
                throw new UserException("No user available to update.");
            }
        } catch (UserException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UserException("Something went wrong while editing the user.");
        }
    }

    public boolean linkGoogleToAccount(Integer userId, String accessTokenGoogle){
            String sub = googleApi.getSub(accessTokenGoogle);

            GoogleUserEntity entity = GoogleUserEntity.builder()
                    .user_id(userId)
                    .sub(sub)
                    .build();

            if(googleUserRepository.existsBySub(sub)){
                throw new UserException("Google account is already linked!");

            }
            else{
                googleUserRepository.save(entity);
                return true;
            }

        }


}
