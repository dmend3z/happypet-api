package com.happypet.HappyPet.service;


import com.happypet.HappyPet.command.auth.CredentialsDto;
import com.happypet.HappyPet.command.auth.LoggedInDto;
import com.happypet.HappyPet.command.auth.PrincipalDto;

public interface AuthService {

    /**
     * Login user
     *
     * @param credentialsDto
     * @return {@link LoggedInDto} logged in user details
     */
    LoggedInDto loginUser(CredentialsDto credentialsDto);

    /**
     * Validate token
     *
     * @param token
     * @return {@link PrincipalDto} principal authenticated
     */
    PrincipalDto validateToken(String token);
}

