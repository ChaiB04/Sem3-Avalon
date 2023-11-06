package individual.individualsem3backend.configuration.security.token;

public interface AccessToken {

    String getRoles();

    Integer getUserId();

    String getEmail();

    String getSubject();

    boolean hasRole(String roleName);
}
