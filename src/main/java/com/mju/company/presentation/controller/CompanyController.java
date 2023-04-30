package com.mju.company.presentation.controller;

import com.mju.company.appliocation.CompanyService;
import com.mju.company.domain.model.Company;
import com.mju.company.domain.model.other.Result.CommonResult;
import com.mju.company.domain.service.ResponseService;
import com.mju.company.presentation.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequiredArgsConstructor
    @RequestMapping("/company-service")
    public class CompanyController {

        private final CompanyService companyService;
        private final ResponseService responseService;

        @GetMapping("/company/get")
        public CommonResult getCompany(){
            List<Company> CompanyList = companyService.getCompany();
            CommonResult commonResult = responseService.getListResult(CompanyList);
            return commonResult;
        }

        @PostMapping("/company/register")
        public CommonResult registerCompany(@RequestBody CompanyDto companyDto) {
                companyService.registerCompany(companyDto);
                return responseService.getSuccessfulResult();
            }

        @PostMapping ("/company/modify/{company_index}")
        public CommonResult modifyCompany(@PathVariable Long company_index, @RequestBody CompanyDto companyDto){
            companyService.modifyCompany(company_index, companyDto);
            return responseService.getSuccessfulResult();
        }

        @DeleteMapping("/company/delete/{company_index}")
        public CommonResult deleteCompany(@PathVariable Long company_index){
            companyService.deleteCompany(company_index);
            return responseService.getSuccessfulResult();
        }

        @GetMapping("/ping")
        public String ping() {
            return "pong";
        }
    }
