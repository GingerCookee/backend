package com.gingercookee.goty.domain.review.service;
import com.gingercookee.goty.domain.Review.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.gingercookee.goty.domain.review.dto.TopicReviewResponseDto;
import com.gingercookee.goty.domain.review.entity.Review;
import com.gingercookee.goty.domain.review.repository.ReviewRepository;
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

    public List<SentimentScoreResponseDto> getSentimentScore(Long appId, YearMonth yearMonth) {
        Date startDate = Date.from(yearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Review> reviews = reviewRepository.findByAppIdAndDateBetween(appId, startDate, endDate);

        return reviews.stream()
                .map(review -> new SentimentScoreResponseDto(
                        review.getReviewId(),
                        review.getUserName(),
                        review.getDate(),
                        review.getContent(),
                        review.getSentiment()
                ))
                .collect(Collectors.toList());
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
}
