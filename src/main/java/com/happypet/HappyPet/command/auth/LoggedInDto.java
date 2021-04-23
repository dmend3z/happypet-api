package com.happypet.HappyPet.command.auth;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for authentication information
 */
@Data
@Builder
public class LoggedInDto {

    private String token;
}