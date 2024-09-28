package com.gingercookee.goty.domain.review.entity;

import com.gingercookee.goty.domain.app.entity.App;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "thumbs")
    private Integer thumbs;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;



}
