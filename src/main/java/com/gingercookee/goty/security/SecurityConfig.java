//package com.gingercookee.goty.security;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/user/signup").permitAll()  // 회원가입 경로 허용
//                        .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
//                );
//
//        return http.build();
//    }
//}
