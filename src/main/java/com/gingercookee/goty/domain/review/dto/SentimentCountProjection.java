package com.gingercookee.goty.domain.Review.dto;

public interface SentimentCountProjection {
    int getNegativeCount();
    int getNeutralCount();
    int getPositiveCount();
}
