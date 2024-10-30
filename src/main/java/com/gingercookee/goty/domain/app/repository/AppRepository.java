package com.gingercookee.goty.domain.app.repository;

import com.gingercookee.goty.domain.app.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App,Long> {
    Optional<App> findByAppId(Long appId);
}
