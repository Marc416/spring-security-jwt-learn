package com.springsecurityjwtlearn.account.application.config;

import com.springsecurityjwtlearn.account.application.common.JwtUtil;
import com.springsecurityjwtlearn.account.application.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private static final String[] AUTH_WHITELIST = {
        "/api/v1/member/**", "/api/auth/**"
    };

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF, CORS
        http.csrf((csrf) -> csrf.disable());
        http.cors(Customizer.withDefaults());

        //세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS));

        //FormLogin, BasicHttp 비활성화
        http.formLogin((form) -> form.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);


        //JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthFilter(customUserDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class);

//        http.exceptionHandling((exceptionHandling) -> exceptionHandling
//            .authenticationEntryPoint(authenticationEntryPoint)
//            .accessDeniedHandler(accessDeniedHandler)
//        );

        // 권한 규칙 작성
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(request -> request.getServletPath().startsWith("/api/v1/user")).hasAnyRole("USER")
                .requestMatchers(request -> request.getServletPath().startsWith("/api/v1/manager")).hasAnyRole("MANAGER")
                .requestMatchers(request -> request.getServletPath().startsWith("/api/v1/admin")).hasAnyRole("ADMIN")
                //@PreAuthrization을 사용할 것이기 때문에 모든 경로에 대한 인증처리는 Pass
//                .anyRequest().permitAll()
                        .anyRequest().authenticated()
        );

        return http.build();
    }
}
