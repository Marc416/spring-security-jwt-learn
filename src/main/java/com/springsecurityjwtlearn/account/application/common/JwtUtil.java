package com.springsecurityjwtlearn.account.application.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.springsecurityjwtlearn.account.application.dto.CustomUserInfoDto;
import com.springsecurityjwtlearn.account.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    private final long accessTokenExpTime;
    private final Algorithm algorithm;


    public JwtUtil(
        @Value("${jwt.secret}") String secretKey,
        @Value("${jwt.expiration_time}") long accessTokenExpTime
    ) {
        this.accessTokenExpTime = accessTokenExpTime;
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    /**
     * Access Token 생성
     * @param member
     * @return Access Token String
     */
    public String createAccessToken(Member member) {
        return createToken(member);
    }


    /**
     * JWT 생성
     * @param member
     * @return JWT String
     */
    private String createToken(Member member) {

        return JWT.create()
            .withExpiresAt(new Date(System.currentTimeMillis()+accessTokenExpTime))
            .withClaim("memberId", member.getMemberId())
            .withClaim("email", member.getEmail())
            .withClaim("name", member.getName())
            .withClaim("role", member.getRole().name())
            .sign(algorithm);


    }


    /**
     * Token에서 User ID 추출
     * @param token
     * @return User ID
     */
    public Long getUserId(String token) {
        return parseClaims(token).get("memberId").asLong();
    }


    /**
     * JWT 검증
     * @param token
     * @return IsValidate
     */
    public boolean validateToken(String token) {
        try {

            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            log.info("JWT claims string is empty.", e);
            throw e;

        }
    }


    /**
     * JWT Claims 추출
     * @param accessToken
     * @return JWT Claims
     */
    public Map<String, Claim> parseClaims(String accessToken) {
        try {
            return JWT.require(algorithm).build().verify(accessToken).getClaims();
        } catch (Exception e) {
            log.info("JWT claims string is empty.", e);
            throw e;
        }
    }
}