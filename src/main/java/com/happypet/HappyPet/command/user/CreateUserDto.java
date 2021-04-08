package com.happypet.HappyPet.command.user;

import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateUserDto {

    @NotBlank(message = "Must have first name")
    private String firstName;

    @NotBlank(message = "Must have last  name")
    private String lastName;

    @NotBlank(message = "Must have phone number")
    private String phoneNumber;

    @NotBlank(message = "Must have email")
    @Email
    private String email;

    @NotBlank(message = "Must have password")
    private String password;

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='***'" +
                '}';
    }
}
