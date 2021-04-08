package com.happypet.HappyPet.command.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UpdateUserDto {

    @NotBlank(message = "Must have first name")
    private String firstName;

    @NotBlank(message = "Must have last  name")
    private String lastName;

    @NotBlank(message = "Must have phone number")
    private String phoneNumber;

    @NotBlank(message = "Must have email")
    private String email;


}
