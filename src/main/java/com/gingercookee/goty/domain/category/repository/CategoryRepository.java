package com.gingercookee.goty.domain.Category.repository;

import com.gingercookee.goty.domain.Category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
}
