package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.configuration.security.token.AccessTokenEncoderDecoder;
import individual.individualsem3backend.configuration.security.token.impl.AccessTokenImpl;
import individual.individualsem3backend.controller.UserRequestResponse.UserLoginResponse;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginManagerImpl implements LoginManager {
    //authentication should be done here

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AccessTokenEncoderDecoder accessTokenEncoderDecoder;

    public UserLoginResponse userLogin(String email, String password){
        if(!email.isEmpty() && !password.isEmpty()){
            User user = userRepository.findByEmail(email);

            if(!matchesPassword(password, user.getPassword())){
                throw new UserException("Invalid Credentials");
            }

            String accessToken = generateAccessToken(user);
            return UserLoginResponse.builder().id(user.getId()).accessToken(accessToken).email(user.getEmail()).build();
        }
        else{
            throw new UserException("Invalid Credentials");
        }

    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(User user){
        if(user.getId() == null){
            throw new UserException("Could not grab user to generate access token.");
        }
        String role = user.getRole().toString();

        String email = user.getEmail();

        Integer id = user.getId();

        return accessTokenEncoderDecoder.encode(
                new AccessTokenImpl(user.getEmail(), email, id, role));
    }

}
