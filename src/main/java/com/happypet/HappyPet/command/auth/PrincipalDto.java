package com.happypet.HappyPet.command.auth;

import com.happypet.HappyPet.enumerators.UserRole;
import lombok.Builder;
import lombok.Data;

/**
 * Principal information
 * principal definition - entity who can authenticate (user, other service, third-parties...)
 */
@Data
@Builder
public class PrincipalDto {
    private long userId;
    private String firstName;
    private UserRole userRole;
}