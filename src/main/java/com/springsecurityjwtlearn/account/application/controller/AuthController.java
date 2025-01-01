package com.springsecurityjwtlearn.account.application.controller;

import com.springsecurityjwtlearn.account.application.dto.JoinRequestDto;
import com.springsecurityjwtlearn.account.application.dto.LoginRequestDto;
import com.springsecurityjwtlearn.account.domain.service.CommandMemberService;
import com.springsecurityjwtlearn.account.domain.service.QueryMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final CommandMemberService commandMemberService;
    private final QueryMemberService queryMemberService;

    public AuthController(
        CommandMemberService commandMemberService,
        QueryMemberService queryMemberService
    ) {
        this.commandMemberService = commandMemberService;
        this.queryMemberService = queryMemberService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        String token = queryMemberService.findMemberByEmailAndPassword(loginRequestDto.email(), loginRequestDto.password());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinRequestDto joinRequestDto){
        commandMemberService.join(joinRequestDto.email(), joinRequestDto.password(), joinRequestDto.name());
        return "<h1>join</h1>";
    }
}
