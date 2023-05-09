package com.mju.company.presentation.controller;

import com.mju.company.appliocation.CompanyService;
import com.mju.company.domain.model.Company;
import com.mju.company.domain.model.Lecturer;
import com.mju.company.domain.model.Notice;
import com.mju.company.domain.model.other.Result.CommonResult;
import com.mju.company.domain.service.ResponseService;
import com.mju.company.presentation.dto.CompanyDto;
import com.mju.company.presentation.dto.LecturerRegisterDto;
import com.mju.company.presentation.dto.NoticeRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequiredArgsConstructor
    @RequestMapping("/company-service")
    public class CompanyController {

    private final CompanyService companyService;
    private final ResponseService responseService;

    // 협력사
    @GetMapping("/company/get")
    public CommonResult getCompany() {
        List<Company> CompanyList = companyService.getCompany();
        CommonResult commonResult = responseService.getListResult(CompanyList);
        return commonResult;
    }

    @PostMapping("/company/register")
    public CommonResult registerCompany(@RequestBody CompanyDto companyDto) {
        companyService.registerCompany(companyDto);
        return responseService.getSuccessfulResult();
    }

    @PostMapping("/company/modify/{company_index}")
    public CommonResult modifyCompany(@PathVariable Long company_index, @RequestBody CompanyDto companyDto) {
        companyService.modifyCompany(company_index, companyDto);
        return responseService.getSuccessfulResult();
    }

    @DeleteMapping("/company/delete/{company_index}")
    public CommonResult deleteCompany(@PathVariable Long company_index) {
        companyService.deleteCompany(company_index);
        return responseService.getSuccessfulResult();
    }

    // 공지사항
    @GetMapping("/notice/get")
    public CommonResult getNotice() {
        List<Notice> NoticeList = companyService.getNotice();
        CommonResult commonResult = responseService.getListResult(NoticeList);
        return commonResult;
    }

    @GetMapping("/notice/get/{lecturer_index}")
    public CommonResult getNoticeList(@PathVariable Long lecturer_index) {
        List<Notice> NoticeList = companyService.getNoticeList(lecturer_index);
        CommonResult commonResult = responseService.getListResult(NoticeList);
        return commonResult;
    }

    @PostMapping("/notice/register/{lecturer_index}")
    public CommonResult registerNotice(@PathVariable Long lecturer_index, @RequestBody NoticeRegisterDto noticeRegisterDto) {
        companyService.registerNotice(lecturer_index, noticeRegisterDto);
        return responseService.getSuccessfulResult();
    }
    @PostMapping("/notice/modify/{notice_index}")
    public CommonResult modifyCompany(@PathVariable Long notice_index, @RequestBody NoticeRegisterDto noticeRegisterDto) {
        companyService.modifyNotice(notice_index, noticeRegisterDto);
        return responseService.getSuccessfulResult();
    }
    @DeleteMapping("/notice/delete/{notice_index}")
    public CommonResult deleteNotice(@PathVariable Long notice_index) {
        companyService.deleteNotice(notice_index);
        return responseService.getSuccessfulResult();
    }
    // 강사진
    @GetMapping("/lecturer/get")
    public CommonResult getLecturer() {
        List<Lecturer> lecturerList = companyService.getLecturer();
        CommonResult commonResult = responseService.getListResult(lecturerList);
        return commonResult;
    }

//    @GetMapping("/lecturer/group/get")
//    public CommonResult getLecturerGroup() {
//        List<Lecturer> lecturerList = companyService.getLecturer();
//        CommonResult commonResult = responseService.getListResult(lecturerList);
//        return commonResult;
//    }


    @PostMapping("/lecturer/register")
    public CommonResult registerLecturer(@RequestBody LecturerRegisterDto lecturerRegisterDto){
        companyService.registerLecturer(lecturerRegisterDto);
        return responseService.getSuccessfulResult();
    }
    @PostMapping("/lecturer/modify/{lecturer_index}")
    public CommonResult modifyLecturer(@PathVariable Long lecturer_index, @RequestBody LecturerRegisterDto lecturerRegisterDto){
        companyService.modifyLecturer(lecturer_index, lecturerRegisterDto);
        return responseService.getSuccessfulResult();
    }
    @DeleteMapping("/lecturer/delete/{lecturer_index}")
    public CommonResult deleteLecturer(@PathVariable Long lecturer_index){
        companyService.deleteLecturer(lecturer_index);
        return responseService.getSuccessfulResult();
    }

}
