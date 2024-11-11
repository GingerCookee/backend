package com.gingercookee.goty.domain.Review.repository;
import com.gingercookee.goty.domain.Review.dto.*;
import com.gingercookee.goty.domain.Review.entity.Review;
import com.gingercookee.goty.domain.topic.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
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

    @Query("SELECT r FROM Review r WHERE r.app.appId = :appId AND r.date BETWEEN :startDate AND :endDate")
    Page<Review> findByAppIdAndDateBetween(@Param("appId") Long appId,
                                           @Param("startDate") Date startDate,
                                           @Param("endDate") Date endDate,
                                           Pageable pageable);
    @Query("SELECT " +
            "SUM(CASE WHEN r.sentiment = -1 THEN 1 ELSE 0 END) as negativeCount, " +
            "SUM(CASE WHEN r.sentiment = 0 THEN 1 ELSE 0 END) as neutralCount, " +
            "SUM(CASE WHEN r.sentiment = 1 THEN 1 ELSE 0 END) as positiveCount " +
            "FROM Review r WHERE r.app.appId = :appId AND r.date BETWEEN :startDate AND :endDate")
    SentimentCountProjection findSentimentCountsByAppIdAndDateRange(
            @Param("appId") Long appId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("SELECT " +
            "YEAR(r.date) as year, " +
            "WEEK(r.date) as week, " +
            "AVG(r.sentiment) as averageSentiment " +
            "FROM Review r " +
            "WHERE r.app.appId = :appId " +
            "GROUP BY YEAR(r.date), WEEK(r.date) " +
            "ORDER BY YEAR(r.date), WEEK(r.date)")
    List<WeeklySentimentProjection> findWeeklyAverageSentimentByAppId(@Param("appId") Long appId);

    @Query("SELECT " +
            "YEAR(r.date) as year, " +
            "MONTH(r.date) as month, " +
            "AVG(r.sentiment) as averageSentiment " +
            "FROM Review r " +
            "WHERE r.app.appId = :appId " +
            "GROUP BY YEAR(r.date), MONTH(r.date) " +
            "ORDER BY YEAR(r.date), MONTH(r.date)")
    List<MonthlySentimentProjection> findMonthlyAverageSentimentByAppId(@Param("appId") Long appId);


    @Query("SELECT r.emotion as emotion, COUNT(r) as count " +
            "FROM Review r " +
            "WHERE r.app.appId = :appId " +
            "AND YEAR(r.date) = :year " +
            "AND MONTH(r.date) = :month " +
            "GROUP BY r.emotion " +
            "ORDER BY r.emotion")
    List<EmotionCountProjection> findEmotionCountsByAppIdAndMonth(@Param("appId") Long appId,
                                                                  @Param("year") int year,
                                                                  @Param("month") int month);

    @Query("SELECT r.date, r.content " +
            "FROM Review r " +
            "WHERE r.app.appId = :appId AND r.emotion = :emotion " +
            "ORDER BY r.date")
    List<Object[]> findReviewsByAppIdAndEmotion(@Param("appId") Long appId,
                                                @Param("emotion") int emotion);

//    List<Review> findByApp_IdAndEmotion(Long appId, String emotion);

    @Query("SELECT r.date, r.content " +
            "FROM Review r " +
            "WHERE r.app.appId = :appId AND r.sentiment = :sentiment " +
            "ORDER BY r.date")
    List<Object[]> findReviewsByAppIdAndSentiment(@Param("appId") Long appId,
                                                  @Param("sentiment") int sentiment);

    @Query("SELECT FUNCTION('DATE_FORMAT', r.date, '%Y-%m') as month, AVG(r.rating) as avgRating " +
            "FROM Review r " +
            "WHERE r.app.appId = :appId " +
            "GROUP BY month " +
            "ORDER BY month")
    List<Object[]> findMonthlyAverageRatingByAppId(@Param("appId") Long appId);
}