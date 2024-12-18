package com.gingercookee.goty.domain.Review.entity;

import com.gingercookee.goty.domain.App.entity.App;
import com.gingercookee.goty.domain.topic.entity.Topic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

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
    private Date date;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "thumbs")
    private Integer thumbs;

    @Column(name = "sentiment")
    private Integer sentiment;

    @Column(name = "emotion")
    private String emotion;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;



}
