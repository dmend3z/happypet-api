package com.happypet.HappyPet.security;


import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserAuthenticationProvider {

    private final AuthService authService;

    public UserAuthenticationProvider(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Validate jwt token
     * @param token
     * @return {@Authentication} with authenticated user
     */
    public Authentication validateToken(String token) {
        // validate kwt token and get principal
        PrincipalDto principal = authService.validateToken(token);

        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(principal.getUserRole().name()))
        );
    }
}
