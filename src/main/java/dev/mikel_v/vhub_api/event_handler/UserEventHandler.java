package dev.mikel_v.vhub_api.event_handler;

import dev.mikel_v.vhub_api.entity.UserEntity;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserEventHandler(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @HandleBeforeSave
    public void handleUserBeforeSave(UserEntity user) {
        if (user.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
    }
}
