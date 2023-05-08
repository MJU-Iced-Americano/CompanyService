package com.mju.company.domain.repository;

import com.mju.company.domain.model.Review;
import com.mju.company.domain.model.ReviewComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ReviewComplaint, Long> {
}
