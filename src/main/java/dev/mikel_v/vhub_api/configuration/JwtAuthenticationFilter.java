package dev.mikel_v.vhub_api.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A filter that intercepts incoming HTTP requests and attempts to authenticate the user based on a JWT token
 * included in the request headers.
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new JwtAuthenticationFilter with the given dependencies.
     * @param jwtService the JwtService to use for token-related operations
     * @param userDetailsService the UserDetailsService to use for retrieving user details
     * @param tokenRepository the TokenRepository to use for storing and retrieving tokens
     */
    @Autowired
    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * A filter that intercepts incoming HTTP requests and checks if the request contains a valid JWT token in its
     * Authorization header. If the token is valid, the filter sets the authenticated user in the SecurityContext.
     * If the token is not valid, the request is rejected and a 401 Unauthorized response is returned to the client.
     * This filter is applied to all HTTP requests except for those that contain "/api/v1/auth" in their path.
     * @param request the incoming HTTP request
     * @param response the HTTP response to be returned to the client
     * @param filterChain the filter chain for the request
     * @throws ServletException if there is a servlet error
     * @throws IOException if there is an input or output error
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;
        System.out.println("hola");
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userName = jwtTokenService.extractUsername(jwt);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if (jwtTokenService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}