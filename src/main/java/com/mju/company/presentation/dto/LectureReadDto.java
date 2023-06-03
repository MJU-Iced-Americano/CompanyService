package com.mju.company.presentation.dto;

import com.mju.company.domain.model.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LectureReadDto {
    private Long lecturerIndex;
    private String lecturerPhoto;
    private String lecturerName;
    private String lecturerCareer;
    private String lecturerAddress;
    private String userId;

    public static LectureReadDto of(Lecturer lecturer){
        return LectureReadDto.builder()
                .lecturerIndex(lecturer.getId())
                .lecturerPhoto(lecturer.getLecturer_photo())
                .lecturerName(lecturer.getLecturer_name())
                .lecturerCareer(lecturer.getLecturer_career())
                .lecturerAddress(lecturer.getLecturer_address())
                .userId(lecturer.getUserId())
                .build();
    }
}
