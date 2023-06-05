package dev.mikel_villota.vlrhub_api.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Represents a response to an authentication request, which contains an access token and a refresh token.
 * The {@link AuthenticationResponse} class uses the {@link JsonProperty} annotation to specify the JSON property names
 * for the access token and the refresh token, which are returned by the authentication server.
 * @author Mikel Villota
 */
public class AuthenticationResponse {


    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    public AuthenticationResponse() {
    }

    /**
     * Creates an instance of {@link AuthenticationResponse} with the specified access token and refresh token.
     * @param accessToken the access token returned by the authentication server
     * @param refreshToken the refresh token returned by the authentication server
     */
    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" + "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationResponse that)) return false;

        if (!Objects.equals(accessToken, that.accessToken)) return false;
        return Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        int result = accessToken != null ? accessToken.hashCode() : 0;
        result = 31 * result + (refreshToken != null ? refreshToken.hashCode() : 0);
        return result;
    }

    /**
     * Returns a new instance of {@link AuthenticationRequest.Builder} to build an {@link AuthenticationRequest} instance.
     * @return a new instance of {@link AuthenticationRequest.Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A builder class for creating an {@link AuthenticationResponse} object.
     * The builder provides methods to set the access token and refresh token values of the authentication response.
     * Returns an instance of {@link AuthenticationRequest} created with the specified phone number and password
     * by calling the {@link #build()} method.
     * Example usage:
     * <pre>{@code
     * AuthenticationResponse response = AuthenticationResponse.builder()
     * .accessToken("abc123")
     * .refreshToken("def456")
     * .build();
     * </pre>
     */
    public static class Builder {
        private final AuthenticationResponse response = new AuthenticationResponse();

        public Builder accessToken(String accessToken) {
            response.setAccessToken(accessToken);
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            response.setRefreshToken(refreshToken);
            return this;
        }

        public AuthenticationResponse build() {
            return response;
        }
    }
}
