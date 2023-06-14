package dev.mikel_v.vhub_api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security Configuration.
 *
 * This class provides the configuration for security in the application. It enables web security
 * and allows customization of various security-related aspects, such as authentication and authorization.
 *
 * Note: This class is annotated with @Configuration and @EnableWebSecurity to indicate that it is a configuration
 * class for web security in the application.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Constructs a new instance of {@code SecurityConfig} with the specified {@code customUserDetailsService}.
     *
     * @param customUserDetailsService the {@code CustomUserDetailsService} used by this configuration
     * @param jwtAuthFilter
     */
    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter jwtAuthFilter){
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Configures the security filter chain to handle incoming requests.
     * @param http the HttpSecurity instance to configure
     * @return the configured security filter chain
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/api/auth/**", "/api/status").permitAll();
                    authorize.anyRequest().authenticated();
                    //authorize.requestMatchers("/api/users/{userid}/**").access(this::hasPermission);
                })
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS
                ))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutUrl("/auth/logout")
                )
                .build();
    }

    /**
     * Bean for the BCryptPasswordEncoder, it configures the encoder and it´s strength.
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * Defines a bean for an {@link AuthenticationManager} configured with the provided {@link AuthenticationConfiguration}.
     * @param config the {@link AuthenticationConfiguration} to configure the {@link AuthenticationManager}
     * @return an {@link AuthenticationManager} instance configured with the provided {@link AuthenticationConfiguration}
     * @throws Exception if an error occurs while configuring the {@link AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    /**
     * Returns the configured authentication provider bean for the application.
     * @return the authentication provider bean.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
