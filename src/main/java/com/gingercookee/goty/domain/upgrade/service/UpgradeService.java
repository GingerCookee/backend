package com.gingercookee.goty.domain.upgrade.service;

import com.gingercookee.goty.domain.app.dto.MetadataResponseDto;
import com.gingercookee.goty.domain.app.entity.App;
import com.gingercookee.goty.domain.category_app.entity.CategoryApp;
import com.gingercookee.goty.domain.upgrade.dto.UpgradeResponseDto;
import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import com.gingercookee.goty.domain.upgrade.repository.UpgradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpgradeService {
    private final UpgradeRepository upgradeRepository;

    public List<UpgradeResponseDto> getUpdateList(Long appId){
        Optional<List<Upgrade>> upgrades = upgradeRepository.findByApp_AppIdOrderByUpgradeDateDesc(appId);

        return upgrades.orElseGet(List::of).stream()
                .map(UpgradeResponseDto::fromEntity) // 변환 로직 적용
                .collect(Collectors.toList());
    }
}
