package com.mju.company.appliocation;

import com.mju.company.domain.model.Company;
import com.mju.company.domain.model.other.Exception.CompanyNotFindException;
import com.mju.company.domain.model.other.Exception.ExceptionList;
import com.mju.company.domain.repository.CompanyRepository;
import com.mju.company.presentation.dto.CompanyDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    @Override
    public List<Company> getCompany() { // 조회
        List<Company> CompanyList = companyRepository.findAll();
        if (!CompanyList.isEmpty()) {
            return CompanyList;
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_FIND_COMPANY);
        }
    }

    @Transactional
    @Override
    public void registerCompany(CompanyDto companyDto) { // 등록
        Company company = new Company(companyDto.getCoCompany_name(), companyDto.getCoCompany_url());
        Company newcompany = Company.builder()
                .CoCompany_name(company.getCoCompany_name())
                .CoCompany_url(company.getCoCompany_url())
                .build();
        companyRepository.save(newcompany);
    }

    @Transactional
    @Override
    public void modifyCompany(Long company_index, CompanyDto companyDto) { // 수정
        Optional<Company> Company = companyRepository.findById(company_index);
        if (Company.isPresent()) {
            Company.get().CompanyUpdate(companyDto.getCoCompany_name(), companyDto.getCoCompany_url());
            companyRepository.save(Company.get());
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_COMPANY);
        }
    }

    @Transactional
    @Override
    public void deleteCompany(Long company_index) { // 삭제
        Optional<Company> Company = companyRepository.findById(company_index);
        if (Company.isPresent()) {
            companyRepository.deleteById(company_index);
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_COMPANY);
        }
    }
}
