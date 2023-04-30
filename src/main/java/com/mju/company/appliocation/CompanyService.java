package com.mju.company.appliocation;

import com.mju.company.domain.model.Company;
import com.mju.company.presentation.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

        public List<Company> getCompany();

        public void registerCompany(CompanyDto companyDto);

        public void modifyCompany(Long company_index, CompanyDto companyDto);

        public void deleteCompany(Long company_index);
}
