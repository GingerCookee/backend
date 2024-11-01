package com.gingercookee.goty.domain.upgrade.repository;

import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpgradeRepository extends JpaRepository<Upgrade, Long> {
    Optional<List<Upgrade>> findByApp_AppIdOrderByUpgradeDateDesc(Long appId);
}
