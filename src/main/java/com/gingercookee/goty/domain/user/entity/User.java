package com.gingercookee.goty.domain.user.entity;

import com.gingercookee.goty.domain.admin.entity.Admin;
import com.gingercookee.goty.domain.bookmark.entity.Bookmark;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private final List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Admin admin;

}
