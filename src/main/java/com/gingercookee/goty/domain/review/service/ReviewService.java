package com.gingercookee.goty.domain.review.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.gingercookee.goty.domain.review.dto.TopicReviewRequestDto;
import com.gingercookee.goty.domain.review.dto.TopicReviewResponseDto;
import com.gingercookee.goty.domain.review.entity.Review;
import com.gingercookee.goty.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<TopicReviewResponseDto> getTopicReview(Long appId, TopicReviewRequestDto topicRequest, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        String[] parts = topicRequest.getMonth().split("-");
        int monthValue = Integer.parseInt(parts[1]);

        Page<Review> reviews = reviewRepository.findByAppIdAndMonth(appId, monthValue, pageable);

        return reviews.map(TopicReviewResponseDto::new).getContent();
    }


}
