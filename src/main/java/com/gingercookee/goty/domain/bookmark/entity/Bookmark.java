package com.gingercookee.goty.domain.bookmark.entity;

import com.gingercookee.goty.domain.App.entity.App;
import com.gingercookee.goty.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
