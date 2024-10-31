package com.gingercookee.goty.domain.review.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gingercookee.goty.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.app.appId = :appId AND MONTH(r.date) = :month")
    Page<Review> findByAppIdAndMonth(@Param("appId") Long appId, @Param("month") int month, Pageable pageable);
}
