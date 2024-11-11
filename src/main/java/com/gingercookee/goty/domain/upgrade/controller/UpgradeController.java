package com.gingercookee.goty.domain.upgrade.controller;

import com.gingercookee.goty.domain.upgrade.dto.UpgradeResponseDto;
import com.gingercookee.goty.domain.upgrade.service.UpgradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UpgradeController {
    private final UpgradeService upgradeService;

    @GetMapping("/{appId}/update")
    public ResponseEntity<List<UpgradeResponseDto>> getUpdateList(@PathVariable("appId") Long appId) {
        return ResponseEntity.ok(upgradeService.getUpdateList(appId));
    }

}
