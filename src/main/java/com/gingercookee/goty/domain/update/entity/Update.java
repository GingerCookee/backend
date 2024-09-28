package com.gingercookee.goty.domain.update.entity;

import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
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
@Table(name = "update")
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "update_id")
    private Long updateId;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;

    @OneToMany(mappedBy = "update")
    private List<Topic> topicList = new ArrayList<>();

    @OneToMany(mappedBy = "udpate")
    private List<Sentiment> sentimentList = new ArrayList<>();

}

