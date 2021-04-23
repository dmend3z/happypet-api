package com.happypet.HappyPet.controller;

import com.happypet.HappyPet.command.auth.CredentialsDto;
import com.happypet.HappyPet.command.auth.LoggedInDto;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.HappypetApiException;
import com.happypet.HappyPet.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for authentication operations
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login user with email and password
     * @param credentials
     * @return {@link LoggedInDto} with user info and jwt token
     */
    @PostMapping("/login")
    public ResponseEntity<LoggedInDto> login(@RequestBody CredentialsDto credentials) {
        LOGGER.info("Request to login user with email {}", credentials.getEmail());

        LoggedInDto loggedIn;
        try {
            loggedIn = authService.loginUser(credentials);

        } catch (HappypetApiException e) {
            // Since RentacarApiException exceptions are thrown by us, we just throw them
            throw e;

        } catch (Exception e) {
            // With all others exceptions we log them and throw a generic exception
            LOGGER.error("Failed to loging user - {}", credentials, e);
            throw new HappypetApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("User logged in successfully. Retrieving jwt token");

        return new ResponseEntity<>(loggedIn, HttpStatus.OK);
    }
}