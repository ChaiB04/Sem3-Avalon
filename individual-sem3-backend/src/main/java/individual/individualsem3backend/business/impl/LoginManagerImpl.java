package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.LoginManager;

import individual.individualsem3backend.business.converters.UserEntityConverter;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.configuration.security.token.AccessTokenEncoderDecoder;
import individual.individualsem3backend.configuration.security.token.impl.AccessTokenImpl;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.domain.enumeration.Role;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginManagerImpl implements LoginManager {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AccessTokenEncoderDecoder accessTokenEncoderDecoder;

    public String userLogin(String email, String password){
        try{
            if(!email.isEmpty() && !password.isEmpty()){

                UserEntity userEntity = userRepository.findByEmail(email);

                if(userEntity == null){
                    throw new UserException("CANNOT FIND USER IN DATABASE");
                }

                User user = UserEntityConverter.userEntityConvertedToUser(userEntity);

                if(user != null){
                    if(!matchesPassword(password, user.getPassword())){
                        throw new UserException("Invalid Credentials");
                    }
                    return generateAccessToken(user);
                }
                else{
                    throw new UserException("Couldn't find user.");
                }



            }
            else{
                throw new UserException("No Input given.");
            }
        }
        catch(Exception ex){
            throw new UserException(ex.getMessage());
        }

    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        try{
            return passwordEncoder.matches(rawPassword, encodedPassword);
        }
        catch(Exception ex){
            throw new UserException("The password cannot be received.");
        }
    }

    private String generateAccessToken(User user){
        try{
            if(user.getId() == null){
                throw new UserException("Could not grab user to generate access token.");
            }


            String role = user.getRole().toString();

            String email = user.getEmail();

            Integer id = user.getId();

            return accessTokenEncoderDecoder.encode(
                    new AccessTokenImpl(email, id, role));
        }
        catch(Exception ex){
            throw new UserException("Cannot generate access token.");
        }
    }

}
