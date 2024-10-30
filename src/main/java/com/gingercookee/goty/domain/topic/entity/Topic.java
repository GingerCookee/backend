package com.gingercookee.goty.domain.topic.entity;

import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "ranking")
    private Integer ranking;

    @Column(name = "count")
    private Integer count;

    @Column(name = "topic")
    private String topic;

    @ManyToOne
    @JoinColumn(name = "upgrade_id")
    private Upgrade upgrade;
}
