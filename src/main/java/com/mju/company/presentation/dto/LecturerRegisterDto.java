package com.mju.company.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LecturerRegisterDto {
    private String lecturer_photo;
    private String lecturer_name;
    private String lecturer_career;
    private String lecturer_address;
}
