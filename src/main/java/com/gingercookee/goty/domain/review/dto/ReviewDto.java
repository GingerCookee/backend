package com.gingercookee.goty.domain.Review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReviewDto {
    private Date date;
    private String content;
}
