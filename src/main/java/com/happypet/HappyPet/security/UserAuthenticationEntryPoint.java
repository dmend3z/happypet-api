package com.happypet.HappyPet.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.happypet.HappyPet.error.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        MAPPER.writeValue(
                httpServletResponse.getOutputStream(),
                Error.builder()
                        .timestamp(new Date())
                        .message(e.getMessage())
                        .method(httpServletRequest.getMethod())
                        .path(httpServletRequest.getServletPath())
                        .exception(e.getClass().getSimpleName())
                        .build()
        );
    }
}
