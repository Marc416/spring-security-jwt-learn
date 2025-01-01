package com.springsecurityjwtlearn.account.application.repository;

import com.springsecurityjwtlearn.account.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 쿼리 메서드
    Member findMemberByEmail(String email);
    Member findByEmailAndPassword(String email, String password);

}