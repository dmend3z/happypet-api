package com.happypet.HappyPet.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "happypet.jwt")
public class JwtProperties {

    private String secretKey;
    private Long expiresIn;
}
