package com.gingercookee.goty.domain.Review.controller;

import com.gingercookee.goty.domain.Review.dto.*;
import com.gingercookee.goty.domain.review.dto.TopicReviewResponseDto;
import com.gingercookee.goty.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

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
    public List<SentimentScoreResponseDto> getSentimentScores(
            @PathVariable Long appId,
            @PathVariable("yearMonth") String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        return reviewService.getSentimentScore(appId, ym);
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
}
