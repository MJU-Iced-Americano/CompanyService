package com.mju.company.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReviewRegisterDto {
    private Long grade;
    private String user_photo;
    private String user_name;
    private String review_content;
}
