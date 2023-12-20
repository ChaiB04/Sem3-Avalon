package individual.individualsem3backend.configuration.security.token;

public interface AccessToken {

    String getRoles();

    Integer getUserId();

    String getSubject();

}
