package com.mju.company.appliocation;

import com.mju.company.domain.model.other.Result.CommonResult;
import com.mju.company.presentation.dto.CompanyEnrollmentDto;

public interface CompanyService {
    public void enrollment(CompanyEnrollmentDto companyEnrollmentDto);
}
