package com.springsecurityjwtlearn.account.application.controller;

import com.springsecurityjwtlearn.account.domain.service.CommandMemberService;
import com.springsecurityjwtlearn.account.domain.service.QueryMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final QueryMemberService queryMemberService;

    public AccountController(
        QueryMemberService queryMemberService
        ) {
        this.queryMemberService = queryMemberService;
    }

    @GetMapping("/user")
    public String user(){
        return "<h1>user</h1>";
    }

    @GetMapping("/manager")
    public String manager(){
        return "<h1>manager</h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1>admin</h1>";
    }
}
