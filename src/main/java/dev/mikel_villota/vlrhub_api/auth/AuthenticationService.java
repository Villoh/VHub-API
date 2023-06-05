package dev.mikel_villota.vlrhub_api.auth;

import dev.mikel_villota.vlrhub_api.configuration.JwtTokenService;
import dev.mikel_villota.vlrhub_api.entity.UserEntity;
import dev.mikel_villota.vlrhub_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
    }

    private AuthenticationResponse register(@Valid UserEntity userEntity){
        var user = UserEntity.builder()
                .email(userEntity.getEmail())
                .userName(userEntity.getUsername())
                .passwordDigest(userEntity.getPasswordDigest())
                .admin(userEntity.getAdmin())
                .verified(userEntity.getAdmin())
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtTokenService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
