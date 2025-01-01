package com.springsecurityjwtlearn.account.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public static Member createUser(String email, String password, String name) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setRole(RoleType.USER);
        return member;
    }

}
