package com.mju.company.presentation.dto;

import com.mju.company.domain.model.other.status.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewDto {
    private Long grade;
    private String user_photo;
    private String user_name;
    private LocalDateTime date;
    private String review_content;
    private Long likedcount;
    private ReviewStatus status;
    private Long declare_count;
}
