package individual.individualsem3backend.business.converters;

import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserEntityConverter {

    private UserEntityConverter(){

    }
    public static User userEntityConvertedToUser(UserEntity entity){
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname()).country(entity.getCountry()).city(entity.getCity())
                .street(entity.getStreet()).housenumber(entity.getHousenumber()).zipcode(entity.getZipcode()).role(entity.getRole())
                .picture(entity.getPicture())
                .phonenumber(entity.getPhonenumber()).build();
    }

    public static UserEntity userConvertedToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstname(user.getFirstname())
                .lastname(user.getLastname()).country(user.getCountry()).city(user.getCity())
                .street(user.getStreet()).housenumber(user.getHousenumber()).zipcode(user.getZipcode()).role(user.getRole())
                .picture(user.getPicture())
                .phonenumber(user.getPhonenumber()).build();
    }
}
