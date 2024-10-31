package com.gingercookee.goty.domain.sentiment.entity;

import com.gingercookee.goty.domain.review.entity.Review;
import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sentiment")
public class Sentiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sentiment_id")
    private Long sentimentId;

    @Column(name = "sentiment_score")
    private Double sentimentScore;

    @Column(name = "weighted_score")
    private Double weightedScore;

    @Column(name = "sentiment")
    private Integer sentiment;

    @Column(name = "month")
    private String month;

    @OneToOne(mappedBy = "sentiment")
    private Review review;


//    미안하다 이것도 지웠다 카톡 확인했지?
//    @ManyToOne
//    @JoinColumn(name = "upgrade_id")
//    private Upgrade upgrade;
}
