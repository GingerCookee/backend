package com.gingercookee.goty.domain.Review.dto;

public interface WeeklySentimentProjection {
    int getYear();
    int getWeek();
    double getAverageSentiment();
}

