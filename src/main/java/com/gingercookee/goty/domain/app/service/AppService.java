package com.gingercookee.goty.domain.App.service;

import com.gingercookee.goty.domain.App.dto.MetadataResponseDto;
import com.gingercookee.goty.domain.App.entity.App;
import com.gingercookee.goty.domain.App.repository.AppRepository;
import com.gingercookee.goty.domain.category_app.entity.CategoryApp;
import com.gingercookee.goty.domain.category_app.repository.CategoryAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppService {
    private final AppRepository appRepository;
    private final CategoryAppRepository categoryAppRepository;


    public MetadataResponseDto getMetadata(Long appId) {
        Optional<App> app = appRepository.findByAppId(appId);

        if (app.isPresent()) {
            Optional<CategoryApp> categoryApp = categoryAppRepository.findByApp(app.get());
            if (categoryApp.isPresent()) {
                return MetadataResponseDto.fromEntities(app.get(), categoryApp.get().getCategory());
            } else {
                return MetadataResponseDto.fromEntity(app.get());
            }
        } else {
            return MetadataResponseDto.builder()
                    .name("Unknown")
                    .publisher("Unknown")
                    .banner("No Banner")
                    .ratingAvg(0.0)
                    .reviewCounts(0)
                    .downloadCounts(0)
                    .recentUpdate(new Date())  // 기본값 예시
                    .category("Uncategorized")
                    .build();
        }

    }
}
