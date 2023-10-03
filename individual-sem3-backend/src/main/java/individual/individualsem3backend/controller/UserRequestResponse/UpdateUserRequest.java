package individual.individualsem3backend.controller.UserRequestResponse;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Integer id;
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
