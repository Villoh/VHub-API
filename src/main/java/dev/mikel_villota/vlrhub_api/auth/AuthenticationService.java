package dev.mikel_villota.vlrhub_api.auth;

import dev.mikel_villota.vlrhub_api.configuration.JwtTokenService;
import dev.mikel_villota.vlrhub_api.entity.UserEntity;
import dev.mikel_villota.vlrhub_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Service class that handles user authentication-related functionality, such as user registration, authentication,
     * and token management.
     * @param userRepository the repository for managing user entities
     * @param jwtTokenService the service for generating and validating JWT tokens
     * @param authenticationManager the authentication manager for validating user credentials
     */
    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtTokenService jwtTokenService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user with the provided information and returns an {@link AuthenticationResponse}
     * containing the generated access and refresh tokens.
     * @param request the {@link RegisterRequest} containing the user's information to be registered
     * @return an {@link AuthenticationResponse} containing the generated access and refresh tokens
     */
    public AuthenticationResponse register(@Valid RegisterRequest registerRequest){
        var user = UserEntity.builder()
                .email(registerRequest.getEmail())
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPasswordDigest()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .admin(registerRequest.getAdmin())
                .verified(registerRequest.getVerified())
                .active(registerRequest.getActive())
                .build();
        userRepository.save(user);
        var jwtToken = jwtTokenService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    /**
     * Authenticates a user by checking the provided username and password against the database. If the authentication succeeds,
     * generates a new JWT token and refresh token for the user, revokes all the user's previous tokens, and returns an
     * {@link AuthenticationResponse} containing the new access and refresh tokens.
     * @param request the {@link AuthenticationRequest} containing the user's phone number and password
     * @return an {@link AuthenticationResponse} containing the new access and refresh tokens
     */
    public AuthenticationResponse authenticate (AuthenticationRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPasswordDigest())
            );
            var user = userRepository.findByUserNameIgnoreCase(request.getUserName())
                    .orElseThrow();
            var jwtToken = jwtTokenService.generateToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } catch (AuthenticationException ex) {
            System.err.println(ex);
        }
        return null;
    }
}
