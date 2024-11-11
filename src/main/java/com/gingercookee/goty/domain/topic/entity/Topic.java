package com.gingercookee.goty.domain.topic.entity;

import com.gingercookee.goty.domain.Review.entity.Review;
import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "month")
    private String month;

    @Column(name = "representation_word")
    private String representationWord;

    @OneToMany(mappedBy = "topic")
    private List<Review> review = new ArrayList<>();

//    미안하다 이거 지웠다 카톡 확인했찌?
//    @ManyToOne
//    @JoinColumn(name = "upgrade_id")
//    private Upgrade upgrade;
}
