package com.mju.company.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NoticeDto {
    private String notice_title;
    private String lecturer_name;
    private LocalDateTime notice_date;
    private String notice_content;
}
