package com.springsecurityjwtlearn.account.application.dto;

public record LoginRequestDto(
    String email,
    String password
) {
}
