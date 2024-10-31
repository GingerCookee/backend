package com.gingercookee.goty.domain.review.controller;

import com.gingercookee.goty.domain.review.dto.TopicReviewRequestDto;
import com.gingercookee.goty.domain.review.dto.TopicReviewResponseDto;
import com.gingercookee.goty.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{appId}/topic")
    public ResponseEntity<List<TopicReviewResponseDto>> getTopicReview(@PathVariable Long appId,
                                                                       @RequestBody TopicReviewRequestDto topicReviewRequest,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        List<TopicReviewResponseDto> topicReviews = reviewService.getTopicReview(appId, topicReviewRequest, page, size);
        return ResponseEntity.ok(topicReviews);
    }


}
