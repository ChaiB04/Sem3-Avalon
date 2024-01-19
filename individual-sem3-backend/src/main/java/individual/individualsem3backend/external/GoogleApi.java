package individual.individualsem3backend.external;
public interface GoogleApi {
    String getAccessToken(String authorizationCode, boolean login);

    String getSub(String jsonElement);
}
