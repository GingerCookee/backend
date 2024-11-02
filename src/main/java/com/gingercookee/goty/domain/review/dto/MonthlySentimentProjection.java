package com.gingercookee.goty.domain.Review.dto;

public interface MonthlySentimentProjection {
    int getYear();
    int getMonth();
    double getAverageSentiment();
}