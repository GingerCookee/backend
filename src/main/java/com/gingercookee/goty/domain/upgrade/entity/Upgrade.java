package com.gingercookee.goty.domain.upgrade.entity;

import com.gingercookee.goty.domain.app.entity.App;
import com.gingercookee.goty.domain.sentiment.entity.Sentiment;
import com.gingercookee.goty.domain.topic.entity.Topic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "upgrade")
public class Upgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upgrade_id")
    private Long upgradeId;

    @Column(name = "upgrade_date")
    private LocalDateTime upgradeDate;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;

//    한달 단위로 끊으면서 upgrade - topic, sentiment 관계를 지워버렸단다

//    @OneToMany(mappedBy = "upgrade")
//    private List<Topic> topicList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "upgrade")
//    private List<Sentiment> sentimentList = new ArrayList<>();

}

