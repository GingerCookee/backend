package com.gingercookee.goty.domain.category_app.repository;

import com.gingercookee.goty.domain.app.entity.App;
import com.gingercookee.goty.domain.category_app.entity.CategoryApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryAppRepository extends JpaRepository<CategoryApp, Long> {
    Optional<CategoryApp> findByApp(App app);
}
