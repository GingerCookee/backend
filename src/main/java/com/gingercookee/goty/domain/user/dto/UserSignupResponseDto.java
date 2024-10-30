package com.gingercookee.goty.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserSignupResponseDto {
    // 정상 코드 : 0, 에러 : 1
    private int status;
}
