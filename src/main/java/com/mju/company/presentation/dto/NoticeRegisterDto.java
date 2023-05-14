package com.mju.company.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeRegisterDto {
    private String notice_title;
    private String lecturer_name;
    private String notice_content;
}
