package com.gingercookee.goty.domain.category_app.entity;

import com.gingercookee.goty.domain.App.entity.App;
import com.gingercookee.goty.domain.Category.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "category_app")
public class CategoryApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_app_id")
    private Long categoryAppId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private App app;


}
