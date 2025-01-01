package com.springsecurityjwtlearn.account.application.dto;

public record JoinRequestDto(
        String email,
        String password,
        String name
) {
}
