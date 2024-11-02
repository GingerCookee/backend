package com.gingercookee.goty.domain.Review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SentimentScoreResponseDto {
    private Long reviewId;
    private String userName;
    private Date date;
    private String content;
    private Integer sentiment;
}
