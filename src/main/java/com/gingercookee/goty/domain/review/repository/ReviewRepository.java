package com.gingercookee.goty.domain.review.repository;
import com.gingercookee.goty.domain.topic.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gingercookee.goty.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.app.appId = :appId AND MONTH(r.date) = :month AND r.topic.topicId = :topicId")
    Page<Review> findByAppIdAndMonthAndTopicId(@Param("appId") Long appId, @Param("month") int month, @Param("topicId") Long topicId, Pageable pageable);

//    @Query("SELECT DISTINCT r.topic FROM Review r WHERE r.app.appId = :appId AND FUNCTION('MONTH', r.date) = :month AND r.sentiment = :sentiment")
//    List<Topic> findTopicsByAppIdAndMonthAndSentiment(@Param("appId") Long appId, @Param("sentiment") Integer sentiment, @Param("month") String month);

    @Query("SELECT DISTINCT t FROM Topic t JOIN t.review r WHERE r.app.appId = :appId AND FUNCTION('MONTH', r.date) = :month AND r.sentiment = :sentiment")
    List<Topic> findDistinctTopicsByAppIdAndMonthAndSentiment(
            @Param("appId") Long appId,
            @Param("sentiment") int sentiment,
            @Param("month") int month
    );
}
