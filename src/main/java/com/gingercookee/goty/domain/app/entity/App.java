package com.gingercookee.goty.domain.app.entity;
import com.gingercookee.goty.domain.admin.entity.Admin;
import com.gingercookee.goty.domain.category_app.entity.CategoryApp;
import com.gingercookee.goty.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "app")
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Long appId;

    @Column(name = "name", nullable = false, unique = true)
    private String memberId;

    @Column(name = "banner_url", nullable = true, unique = true)
    private String bannerUrl;

    @Column(name = "publisher", nullable = false, unique = false)
    private String publisher;

    @Column(name = "download_count", nullable = false, unique = false)
    private Integer downloadCount;

    @Column(name = "review_count", nullable = false, unique = false)
    private Integer reviewCount;

    @Column(name = "rating_avg", nullable = false, unique = false)
    private Double ratingAvg;

    @OneToMany(mappedBy = "app")
    private List<CategoryApp> categoryAppList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "app")
    private List<Review> reviewList = new ArrayList<>();





}
