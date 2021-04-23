package com.happypet.HappyPet.command.auth;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CredentialsDto {

    @Email(message = "Email should be correctly formatted")
    private String email;

    @NotBlank(message = "Password must be provided")
    private String password;

    /**
     * Override to String to avoid show the password
     * in the logs if printing the entire object
     * @return
     */
    @Override
    public String toString() {
        return "CredentialsDto{" +
                "email='" + email + '\'' +
                ", password='***'" +
                '}';
    }
}
