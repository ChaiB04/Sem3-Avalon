package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.configuration.security.token.AccessTokenEncoderDecoder;
import individual.individualsem3backend.configuration.security.token.impl.AccessTokenImpl;
import individual.individualsem3backend.controller.UserRequestResponse.UserLoginGetUserResponse;
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

    public UserLoginGetUserResponse userLogin(String email, String password){
        if(!email.isEmpty() && !password.isEmpty()){
            User user = userRepository.findByEmailAndPassword(email, password);

            if(!matchesPassword(password, user.getPassword())){
                throw new UserException("Invalid Credentials");
            }

            String accessToken = generateAccessToken(user);
            return UserLoginGetUserResponse.builder().id(user.getId()).accessToken(accessToken).email(user.getEmail()).build();
        }
        else{
            throw new UserException("Invalid Credentials");
        }

    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(User user){
        Integer userId = user.getId() != null ? user.getId() : null;

        String role = user.getRole().toString();

        String email = user.getEmail();

        return accessTokenEncoderDecoder.encode(
                new AccessTokenImpl(user.getEmail(), email, userId, role));
    }

}
