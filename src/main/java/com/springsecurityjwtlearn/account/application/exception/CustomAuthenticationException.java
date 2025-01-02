package com.springsecurityjwtlearn.account.application.exception;

import com.springsecurityjwtlearn.account.application.common.CodeEnum;
import lombok.Getter;

import java.util.Map;

@Getter
public class CustomAuthenticationException extends RuntimeException {
    private final CodeEnum code;
    private final Map<String, Object> data;
    public CustomAuthenticationException(String message, Map<String, Object> data) {
        super(message);
        this.code = CodeEnum.FRS_004;
        this.data = data;
    }
}
