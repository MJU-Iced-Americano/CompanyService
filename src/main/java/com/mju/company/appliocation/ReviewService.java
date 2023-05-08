package com.mju.company.appliocation;

import com.mju.company.domain.model.Review;
import com.mju.company.presentation.dto.ReviewComplaintDto;
import com.mju.company.presentation.dto.ReviewDto;
import com.mju.company.presentation.dto.ReviewRegisterDto;

import java.util.List;

public interface ReviewService {
    List<Review> getReview();

    void registerReview(ReviewRegisterDto reviewRegisterDto);

    void deleteReview(Long review_index);

    void incrementLiked(Long review_index);

    void decrementLiked(Long review_index);

    List<Review> getDGradeReview();

    List<Review> getAGradeReview();

    List<Review> getDate();

    List<Review> getLiked();

    void complaint(Long review_index, ReviewComplaintDto reviewComplaintDto);
}
