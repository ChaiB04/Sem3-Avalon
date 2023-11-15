package individual.individualsem3backend.configuration.security.token;

import individual.individualsem3backend.domain.enumeration.Role;

public interface AccessToken {

    String getRoles();

    Integer getUserId();

    String getSubject();

}
