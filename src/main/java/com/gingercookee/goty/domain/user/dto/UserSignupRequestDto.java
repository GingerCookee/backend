package com.gingercookee.goty.domain.user.dto;

import com.gingercookee.goty.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {
    private String loginId;
    private String password;
    private String email;

    public User toEntity(){
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
