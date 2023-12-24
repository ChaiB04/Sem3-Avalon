package individual.individualsem3backend.external;

import com.google.gson.JsonElement;

public interface GoogleApi {
    String getAccessToken(String authorizationCode);

    String getSub(String jsonElement);
}
