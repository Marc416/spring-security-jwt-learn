package com.springsecurityjwtlearn.account.domain.service;

import com.springsecurityjwtlearn.account.application.repository.MemberRepository;
import com.springsecurityjwtlearn.account.domain.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class CommandMemberService {
    private MemberRepository memberRepository;

    public CommandMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(String email, String password, String name) {
        Member member = Member.createUser(email, password, name);
        memberRepository.save(member);
    }

}
