package com.springsecurityjwtlearn.account.application.config;

import com.springsecurityjwtlearn.account.application.common.httpresponse.HttpApiResponse;
import com.springsecurityjwtlearn.account.application.exception.CustomAccessDeniedException;
import com.springsecurityjwtlearn.account.application.exception.ApplicationException;
import com.springsecurityjwtlearn.account.application.exception.CustomAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({CustomAccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(CustomAccessDeniedException e) {
        e.printStackTrace();
        return ResponseEntity.status(403).body(
            HttpApiResponse.builder()
                .code(e.getCode())
                .data(e.getData())
                .message(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler({CustomAuthenticationException.class})
    public ResponseEntity handleAuthenticationException(CustomAuthenticationException e) {
        e.printStackTrace();
        return ResponseEntity.status(401).body(
            HttpApiResponse.builder()
                .code(e.getCode())
                .data(e.getData())
                .message(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity handleApplicationException(ApplicationException e) {
        e.printStackTrace();
        return ResponseEntity.status(400).body(
            HttpApiResponse.fromExceptionMessage(e.getMessage(), e.getData())
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleSystemException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body(
            HttpApiResponse.fromExceptionMessage(e.toString())
        );
    }
}
