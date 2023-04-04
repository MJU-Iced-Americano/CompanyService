package com.mju.company.appliocation;

import com.mju.company.domain.model.Company;
import com.mju.company.domain.model.other.Result.CommonResult;
import com.mju.company.domain.repository.CompanyRepository;
import com.mju.company.domain.service.ResponseService;
import com.mju.company.presentation.dto.CompanyEnrollmentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ResponseService responseService;

    @Transactional
    public CommonResult enrollment(CompanyEnrollmentDto companyEnrollmentDto) {
        Company company = new Company(companyEnrollmentDto.getName(), companyEnrollmentDto.getUrl());
        companyRepository.save(company);
        return responseService.getSuccessfulResult();
    }
}
