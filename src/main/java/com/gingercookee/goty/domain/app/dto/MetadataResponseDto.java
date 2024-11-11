package com.gingercookee.goty.domain.App.dto;
import com.gingercookee.goty.domain.App.entity.App;
import com.gingercookee.goty.domain.Category.entity.Category;
import com.gingercookee.goty.domain.category_app.entity.CategoryApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.boot.Metadata;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetadataResponseDto {
    private String name;
    private String publisher;
    private String banner;
    private double ratingAvg;
    private int reviewCounts;
    private int downloadCounts;
    private Date recentUpdate;
    private String category;

    public static MetadataResponseDto fromEntities(App app, Category category) {
        return  MetadataResponseDto.builder()
                .name(app.getName())
                .publisher(app.getPublisher())
                .banner(app.getBannerUrl())
                .ratingAvg(app.getRatingAvg())
                .reviewCounts(app.getReviewCount())
                .downloadCounts(app.getDownloadCount())
                .recentUpdate(app.getRecentUpdate())
                .category(category.getCategoryName())
                .build();

    }
    public static MetadataResponseDto fromEntity(App app) {
        return MetadataResponseDto.builder()
                .name(app.getName())
                .publisher(app.getPublisher())
                .banner(app.getBannerUrl())
                .ratingAvg(app.getRatingAvg())
                .reviewCounts(app.getReviewCount())
                .downloadCounts(app.getDownloadCount())
                .recentUpdate(app.getRecentUpdate())
                .category("Unknown")
                .build();
    }
}


