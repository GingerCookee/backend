package com.gingercookee.goty.domain.sentiment.entity;

import com.gingercookee.goty.domain.update.entity.Update;
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

    @Column(name = "sentimentScore")
    private Double sentimentScore;

    @Column(name = "weightedScore")
    private Double weightedScore;

    @Column(name = "sentiment")
    private Integer sentiment;

    @ManyToOne
    @JoinColumn(name = "update_id")
    private Update update;
}
