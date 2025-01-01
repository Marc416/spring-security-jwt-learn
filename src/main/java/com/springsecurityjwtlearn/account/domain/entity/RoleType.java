package com.springsecurityjwtlearn.account.domain.entity;

public enum RoleType {
    USER("사용자"),
    ADMIN("관리자");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }
}
