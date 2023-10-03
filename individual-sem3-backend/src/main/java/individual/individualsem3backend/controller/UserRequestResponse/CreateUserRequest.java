package individual.individualsem3backend.controller.UserRequestResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String street;
    private Integer housenumber;
    private String zipcode;
    private String city;
    private String country;
    private String phonenumber;
}
