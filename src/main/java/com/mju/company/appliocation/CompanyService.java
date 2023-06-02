package com.mju.company.appliocation;

import com.mju.company.domain.model.Company;
import com.mju.company.domain.model.Lecturer;
import com.mju.company.domain.model.Notice;
import com.mju.company.presentation.dto.CompanyDto;
import com.mju.company.presentation.dto.LecturerRegisterDto;
import com.mju.company.presentation.dto.NoticeRegisterDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService {

        public List<Company> getCompany();

        public void registerCompany(String userId, CompanyDto companyDto, MultipartFile images);

        public void modifyCompany(String userId, Long company_index, CompanyDto companyDto, MultipartFile images);

        public void deleteCompany(String userId, Long company_index);

        public List<Notice> getNotice();
        public List<Notice> getNoticeList(Long lecturer_index);
        public void registerNotice(Long lecturer_index, NoticeRegisterDto noticeRegisterDto);
        public void modifyNotice(Long notice_index, NoticeRegisterDto noticeRegisterDto);
        public void deleteNotice(Long notice_index);

        public List<Lecturer> getLecturer();
//        public List<Lecturer.LecturerGroup> getLecturerGroup();
        public void registerLecturer(LecturerRegisterDto lecturerRegisterDto);
        public void modifyLecturer(Long lecturer_index, LecturerRegisterDto lecturerRegisterDto);
        public void deleteLecturer(Long lecturer_index);
}
