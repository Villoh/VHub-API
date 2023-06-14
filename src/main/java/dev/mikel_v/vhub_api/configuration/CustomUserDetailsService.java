package dev.mikel_v.vhub_api.configuration;

import dev.mikel_v.vhub_api.entity.UserEntity;
import dev.mikel_v.vhub_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom User Details Service.
 *
 * This class implements the UserDetailsService interface and provides custom logic
 * for loading user details during the authentication process.
 *
 * Note: This class is annotated with @Service to indicate that it is a service component
 * responsible for loading user details.
 *
 * It is typically used by the authentication mechanism to load user-specific data such as
 * username, password, and authorities (roles) for authentication and authorization purposes.
 *
 * Implementing the UserDetailsService interface allows for customizing the way user details
 * are retrieved and authenticated in the application.
 *
 * @see UserDetailsService
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new instance of {@code CustomUserDetailsService} with the specified {@code userRepository}.
     * @param userRepository the {@code UserRepository} used by this configuration
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserNameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
