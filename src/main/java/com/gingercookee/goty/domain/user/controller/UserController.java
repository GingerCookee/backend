package com.gingercookee.goty.domain.user.controller;

import com.gingercookee.goty.domain.user.dto.UserSigninRequestDto;
import com.gingercookee.goty.domain.user.dto.UserSigninResponseDto;
import com.gingercookee.goty.domain.user.dto.UserSignupRequestDto;
import com.gingercookee.goty.domain.user.dto.UserSignupResponseDto;
import com.gingercookee.goty.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<UserSignupResponseDto> signupUser(@RequestBody UserSignupRequestDto userDto){
        return ResponseEntity.ok(userService.signupUser(userDto));
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<UserSigninResponseDto> signinUser(@RequestBody UserSigninRequestDto userDto){
        return ResponseEntity.ok(userService.signinUser(userDto));
    }
}
