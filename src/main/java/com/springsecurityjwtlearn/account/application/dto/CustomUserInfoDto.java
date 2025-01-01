package com.springsecurityjwtlearn.account.application.dto;

public record CustomUserInfoDto(
    Long memberId,

    String email,

    String name,

    String password,

    String role
) {
}
