package com.springsecurityjwtlearn.account.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurityjwtlearn.account.application.config.CustomUserDetails;
import com.springsecurityjwtlearn.account.application.dto.CustomUserInfoDto;
import com.springsecurityjwtlearn.account.application.repository.MemberRepository;
import com.springsecurityjwtlearn.account.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.parseLong(id))
            .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        CustomUserInfoDto dto = mapper.convertValue(member, CustomUserInfoDto.class);

        return new CustomUserDetails(dto);
    }
}