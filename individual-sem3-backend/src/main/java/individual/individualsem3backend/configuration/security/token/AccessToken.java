package individual.individualsem3backend.configuration.security.token;

import java.util.Set;

public interface AccessToken {

    String getRoles();

    Integer getUserId();

    String getEmail();

    String getSubject();

    boolean hasRole(String roleName);
}
