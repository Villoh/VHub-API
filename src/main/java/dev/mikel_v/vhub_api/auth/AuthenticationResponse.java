package dev.mikel_v.vhub_api.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response to an authentication request, which contains an access token and a refresh token.
 * The {@link AuthenticationResponse} class uses the {@link JsonProperty} annotation to specify the JSON property names
 * for the access token and the refresh token, which are returned by the authentication server.
 * @author Mikel Villota
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

}
