package com.gingercookee.goty.domain.Review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SentimentCountDto {
    private int negativeCount;
    private int neutralCount;
    private int positiveCount;
}
