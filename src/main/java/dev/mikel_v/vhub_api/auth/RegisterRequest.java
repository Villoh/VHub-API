package dev.mikel_v.vhub_api.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to register a new user.
 * The {@link RegisterRequest} class contains the phone number, full name, password, and admin status of the new user.
 * @author Mikel Villota
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {
    private String userName;
    private String email;
    private String passwordDigest;
    private Boolean verified;
    private Boolean admin;
    private Boolean active;
}
