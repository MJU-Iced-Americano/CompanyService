package com.mju.company.presentation.dto;

import com.mju.company.domain.model.ReviewComplaint;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewComplaintDto {
    private String complaintContent;
    private ReviewComplaint.ComplaintType type;
}
