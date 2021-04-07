package com.happypet.HappyPet.command.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {

    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
