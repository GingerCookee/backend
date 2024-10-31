package com.gingercookee.goty.domain.upgrade.repository;

import com.gingercookee.goty.domain.upgrade.entity.Upgrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpgradeRepository extends JpaRepository<Upgrade, Long> {
}
