package com.gingercookee.goty.domain.upgrade.dto;

import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpgradeResponseDto {
    private String content;
    private LocalDateTime upgradeDate;

    public static UpgradeResponseDto fromEntity(Upgrade upgrade){
        return UpgradeResponseDto.builder()
                .content(upgrade.getContent())
                .upgradeDate(upgrade.getUpgradeDate())
                .build();
    }
}
