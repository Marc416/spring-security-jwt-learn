package com.springsecurityjwtlearn.account.domain.service;

import com.springsecurityjwtlearn.account.application.common.JwtUtil;
import com.springsecurityjwtlearn.account.application.repository.MemberRepository;
import com.springsecurityjwtlearn.account.domain.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class QueryMemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public QueryMemberService(
        MemberRepository memberRepository,
        JwtUtil jwtUtil
    ) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String findMemberByEmailAndPassword(String email, String password) {
        Member member =  memberRepository.findByEmailAndPassword(email, password);
        return jwtUtil.createAccessToken(member);
    }
}
