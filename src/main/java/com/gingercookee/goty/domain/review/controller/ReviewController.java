package com.gingercookee.goty.domain.Review.controller;

import com.gingercookee.goty.domain.Review.dto.*;
import com.gingercookee.goty.domain.review.dto.TopicReviewResponseDto;
import com.gingercookee.goty.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("{appId}//topicReview/{month}/{topicId}")
    public ResponseEntity<List<TopicReviewResponseDto>> getTopicReview(@PathVariable Long appId,
                                                                       @PathVariable String month,
                                                                       @PathVariable Long topicId,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        List<TopicReviewResponseDto> topicReviews = reviewService.getTopicReview(appId, month, topicId, page, size);
        return ResponseEntity.ok(topicReviews);
    }
    @GetMapping("{appId}/sentimentScore/{yearMonth}")
    public Page<SentimentScoreResponseDto> getSentimentScores(
            @PathVariable Long appId,
            @PathVariable("yearMonth") String yearMonth,
            Pageable pageable) {
        YearMonth ym = YearMonth.parse(yearMonth);
        return reviewService.getSentimentScore(appId, ym, pageable);
    }

    @GetMapping("{appId}/sentimentCount/{yearMonth}")
    public SentimentCountDto getSentimentCount(
            @PathVariable Long appId,
            @PathVariable("yearMonth") String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        return reviewService.getSentimentCountByAppIdAndYearMonth(appId, ym);
    }

    @GetMapping("/{appId}/avgSentiment/week")
    public List<WeeklySentimentProjection> getWeeklyAverageSentiment(@PathVariable Long appId) {
        return reviewService.getWeeklyAverageSentiment(appId);
    }

    @GetMapping("/{appId}/avgSentiment/month")
    public List<MonthlySentimentProjection> getMonthlyAverageSentiment(@PathVariable Long appId) {
        return reviewService.getMonthlyAverageSentiment(appId);
    }
    @GetMapping("/{appId}/emotion/{yearMonth}")
    public List<EmotionCountProjection> getEmotionCounts(@PathVariable Long appId,
                                                         @PathVariable("yearMonth") String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        return reviewService.getEmotionCountsByAppIdAndMonth(appId, ym);
    }
    @GetMapping("/{appId}/emotion/reviews/{num}")
    public List<ReviewDto> getReviewsByEmotion(@PathVariable Long appId, @PathVariable int num) {
        // emotion을 int num으로 받기 때문에, 해당 int 값을 바로 넘김
        return reviewService.getReviewsByAppIdAndEmotion(appId, num);


    }
    @GetMapping("/{appId}/sentiment/reviews/{num}")
    public List<ReviewDto> getReviewsBySentiment(@PathVariable Long appId, @PathVariable int num) {
        return reviewService.getReviewsBySentiment(appId, num);
    }

    @GetMapping("/{appId}/rating")
    public Map<String, List<RatingDto>> getMonthlyAverageRating(@PathVariable Long appId) {
        List<RatingDto> ratesList = reviewService.getMonthlyAverageRating(appId);
        Map<String, List<RatingDto>> response = new HashMap<>();
        response.put("rates_list", ratesList);
        return response;
    }

}
