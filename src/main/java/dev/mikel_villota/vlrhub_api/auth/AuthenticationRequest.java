package dev.mikel_villota.vlrhub_api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an authentication request  {@link AuthenticationRequest} with a phone number and a password.
 * @author Mikel Villota
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String userName;
    private String passwordDigest;

}
