package com.springsecurityjwtlearn.account.application.config;

import com.springsecurityjwtlearn.account.application.common.httpresponse.HttpApiResponse;
import com.springsecurityjwtlearn.account.application.config.jsonmapper.JsonMapper;
import com.springsecurityjwtlearn.account.application.exception.CustomAuthenticationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final JsonMapper jsonMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        log.error("Not Authenticated Request", authException);
        log.error("Request Uri : {}", request.getRequestURI());
        CustomAuthenticationException exception = new CustomAuthenticationException("Not Authenticated Request", null);

        HttpApiResponse body = HttpApiResponse.builder()
            .code(exception.getCode())
            .data(null)
            .message(exception.getMessage())
            .build();
        String responseBody = jsonMapper.writeValueAsString(body);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseBody);
    }
}