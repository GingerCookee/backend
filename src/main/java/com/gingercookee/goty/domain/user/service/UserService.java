package com.gingercookee.goty.domain.user.service;

import com.gingercookee.goty.domain.user.dto.UserSigninRequestDto;
import com.gingercookee.goty.domain.user.dto.UserSigninResponseDto;
import com.gingercookee.goty.domain.user.dto.UserSignupRequestDto;
import com.gingercookee.goty.domain.user.dto.UserSignupResponseDto;
import com.gingercookee.goty.domain.user.entity.User;
import com.gingercookee.goty.domain.user.repository.UserRepository;
import com.gingercookee.goty.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 정상 코드 : 0, 에러 : 1
    public UserSignupResponseDto signupUser(UserSignupRequestDto userSignupRequestDto) {
        Optional<User> user = userRepository.findByLoginId(userSignupRequestDto.getLoginId());

        if (user.isEmpty()) {
            // User 엔티티 생성 후 저장
            User newUser = userRepository.save(userSignupRequestDto.toEntity());
            return UserSignupResponseDto.builder()
                    .status(0)  // 성공
                    .build();
        } else {
            return UserSignupResponseDto.builder().status(1).build();  // 에러: 이미 존재하는 사용자
        }
    }

    // 정상 코드 : 0(JWT 토큰 반환), 아이디 미존재 : 1, 비밀번호 불일치 : 2
    public UserSigninResponseDto signinUser(UserSigninRequestDto userSigninRequestDto) {
        Optional<User> user = userRepository.findByLoginId(userSigninRequestDto.getLoginId());

        if (user.isPresent()) {
            if (user.get().getPassword().equals(userSigninRequestDto.getPassword())) {
                // JWT 토큰 생성
                String token = jwtTokenProvider.createToken(userSigninRequestDto.getLoginId());
                return UserSigninResponseDto.builder()
                        .status(0)  // 성공
                        .token(token)  // 생성된 JWT 토큰
                        .build();
            } else {
                return UserSigninResponseDto.builder().status(2).build();  // 비밀번호 불일치
            }
        } else {
            return UserSigninResponseDto.builder().status(1).build();  // 아이디 미존재
        }
    }
}
