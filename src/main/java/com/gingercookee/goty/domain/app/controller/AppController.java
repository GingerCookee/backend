package com.gingercookee.goty.domain.App.controller;

import com.gingercookee.goty.domain.App.dto.MetadataResponseDto;
import com.gingercookee.goty.domain.App.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/{appId}/meta")
    public ResponseEntity<MetadataResponseDto> signupUser(@PathVariable("appId") Long appId) {
        return ResponseEntity.ok(appService.getMetadata(appId));

    }
}
