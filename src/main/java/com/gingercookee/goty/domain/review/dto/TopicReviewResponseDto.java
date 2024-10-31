package com.gingercookee.goty.domain.review.dto;

import com.gingercookee.goty.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicReviewResponseDto {
    private Long reviewId;
    private String userName;
    private Date date;
    private String content;
    private int rating;
    private int thumbs;

    public TopicReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.userName = review.getUserName();
        this.date = review.getDate();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.thumbs = review.getThumbs();
    }
}
