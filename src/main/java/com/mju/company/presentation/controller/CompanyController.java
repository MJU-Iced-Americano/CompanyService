package com.mju.company.presentation.controller;

import com.mju.company.appliocation.CompanyService;
import com.mju.company.domain.model.other.Result.CommonResult;
import com.mju.company.domain.service.ResponseService;
import com.mju.company.presentation.dto.CompanyEnrollmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company-service")
public class CompanyController {

    private final CompanyService companyService;
    private final ResponseService responseService;

    @PostMapping("/enrollment")
    public CommonResult enrollment(@RequestBody CompanyEnrollmentDto companyEnrollmentDto) {
        companyService.enrollment(companyEnrollmentDto);
        return responseService.getSuccessfulResult();
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
