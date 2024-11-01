package com.gingercookee.goty.domain.topic.repository;

import com.gingercookee.goty.domain.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
//    @Query("SELECT t FROM Topic t WHERE t.review.wj  = :appId AND t.month = :month")
//    Optional<Topic> findByAppIdAndMonth(Long appId, String month);

}
