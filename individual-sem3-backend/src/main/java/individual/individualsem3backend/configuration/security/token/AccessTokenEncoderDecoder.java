package individual.individualsem3backend.configuration.security.token;

public interface AccessTokenEncoderDecoder {
    String encode(AccessToken accessToken);

    AccessToken decode(String accessTokenEncoded);
}
