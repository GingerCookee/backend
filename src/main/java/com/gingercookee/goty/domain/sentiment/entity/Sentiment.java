package com.gingercookee.goty.domain.sentiment.entity;

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

    @ManyToOne
    @JoinColumn(name = "upgrade_id")
    private Upgrade upgrade;
}
