package com.gingercookee.goty.domain.Review.service;
import com.gingercookee.goty.domain.Review.dto.*;
import com.gingercookee.goty.domain.Review.entity.Review;
import com.gingercookee.goty.domain.Review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.gingercookee.goty.domain.Review.dto.TopicReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<TopicReviewResponseDto> getTopicReview(Long appId, String month, Long topicId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        String[] parts = month.split("-");
        int monthValue = Integer.parseInt(parts[1]);

        Page<Review> reviews = reviewRepository.findByAppIdAndMonthAndTopicId(appId, monthValue, topicId, pageable);

        return reviews.map(TopicReviewResponseDto::new).getContent();
    }

    public Page<SentimentScoreResponseDto> getSentimentScore(Long appId, YearMonth yearMonth, Pageable pageable) {
        Date startDate = Date.from(yearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Page<Review> reviews = reviewRepository.findByAppIdAndDateBetween(appId, startDate, endDate, pageable);

        return reviews.map(review -> new SentimentScoreResponseDto(
                review.getReviewId(),
                review.getUserName(),
                review.getDate(),
                review.getContent(),
                review.getSentiment()
        ));
    }

    public SentimentCountDto getSentimentCountByAppIdAndYearMonth(Long appId, YearMonth yearMonth) {
        Date startDate = Date.from(yearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // 쿼리 결과를 SentimentCountProjection 인터페이스로 받아서 사용
        SentimentCountProjection result = reviewRepository.findSentimentCountsByAppIdAndDateRange(appId, startDate, endDate);

        if (result != null) {
            return new SentimentCountDto(result.getNegativeCount(), result.getNeutralCount(), result.getPositiveCount());
        } else {
            // 결과가 비어있는 경우 기본값 반환
            return new SentimentCountDto(0, 0, 0);
        }
    }
    public List<WeeklySentimentProjection> getWeeklyAverageSentiment(Long appId) {
        return reviewRepository.findWeeklyAverageSentimentByAppId(appId);
    }

    public List<MonthlySentimentProjection> getMonthlyAverageSentiment(Long appId) {
        return reviewRepository.findMonthlyAverageSentimentByAppId(appId);
    }

    public List<EmotionCountProjection> getEmotionCountsByAppIdAndMonth(Long appId, YearMonth yearMonth) {
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        return reviewRepository.findEmotionCountsByAppIdAndMonth(appId, year, month);
    }

    public List<ReviewDto> getReviewsByAppIdAndEmotion(Long appId, int emotion) {
        List<Object[]> results = reviewRepository.findReviewsByAppIdAndEmotion(appId, emotion);
        return results.stream()
                .map(row -> new ReviewDto((Date) row[0], (String) row[1]))
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsBySentiment(Long appId, int num) {
        int sentiment = mapSentiment(num);
        List<Object[]> results = reviewRepository.findReviewsByAppIdAndSentiment(appId, sentiment);
        return results.stream()
                .map(row -> new ReviewDto((Date) row[0], (String) row[1]))
                .collect(Collectors.toList());
    }

    private int mapSentiment(int num) {
        switch (num) {
            case 0: return -1; // 부정
            case 1: return 0;  // 중립
            case 2: return 1;  // 긍정
            default: throw new IllegalArgumentException("Invalid sentiment number: " + num);
        }
    }

    public List<RatingDto> getMonthlyAverageRating(Long appId) {
        List<Object[]> results = reviewRepository.findMonthlyAverageRatingByAppId(appId);
        return results.stream()
                .map(row -> new RatingDto((String) row[0], (Double) row[1]))
                .collect(Collectors.toList());
    }
}
