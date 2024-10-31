package com.gingercookee.goty.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicReviewRequestDto {
    private String month;
}
