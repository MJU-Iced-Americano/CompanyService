package com.mju.company.appliocation;

import com.mju.company.domain.model.Company;
import com.mju.company.domain.model.Lecturer;
import com.mju.company.domain.model.Notice;
import com.mju.company.domain.model.other.Exception.CompanyNotFindException;
import com.mju.company.domain.model.other.Exception.ExceptionList;
import com.mju.company.domain.repository.CompanyRepository;
import com.mju.company.domain.repository.LecturerRepository;
import com.mju.company.domain.repository.NoticeRepository;
import com.mju.company.domain.service.S3Service;
import com.mju.company.presentation.dto.CompanyDto;
import com.mju.company.presentation.dto.LecturerRegisterDto;
import com.mju.company.presentation.dto.NoticeRegisterDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final NoticeRepository noticeRepository;
    private final LecturerRepository lecturerRepository;
    private final S3Service s3Service;

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
    public void registerCompany(CompanyDto companyDto, MultipartFile images) { // 등록
        if (!images.isEmpty()) {
            String imageUrl = s3Service.uploadImageToS3(images);
            companyDto.setCoCompany_photo_url(imageUrl);
        }
        Company company = new Company(companyDto.getCoCompany_name(), companyDto.getCoCompany_url(), companyDto.getCoCompany_photo_url());
//        Company newcompany = Company.builder()
//                .CoCompany_name(company.getCoCompany_name())
//                .CoCompany_url(company.getCoCompany_url())
//                .CoCompany_photo_url(company.getCoCompany_photo_url())
//                .build();
        companyRepository.save(company);
    }

    @Transactional
    @Override
    public void modifyCompany(Long company_index, CompanyDto companyDto, MultipartFile images) { // 수정
        Optional<Company> optionalCompany = companyRepository.findById(company_index);
        if (optionalCompany.isPresent()) {
            if (!images.isEmpty()) {
                // 기존 이미지 삭제
                Company company = optionalCompany.get();
                String imageUrl = company.getCoCompany_photo_url();
                s3Service.deleteImageFromS3(imageUrl);

                // 새 이미지 추가
                String photoUrl = s3Service.uploadImageToS3(images);
                companyDto.setCoCompany_photo_url(photoUrl);
                optionalCompany.get().CompanyUpdate(companyDto.getCoCompany_name(), companyDto.getCoCompany_url(), companyDto.getCoCompany_photo_url());
            }
            companyRepository.save(optionalCompany.get());
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_COMPANY);
        }
    }

    @Transactional
    @Override
    public void deleteCompany(Long company_index) { // 삭제
        Optional<Company> optionalCompany = companyRepository.findById(company_index);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            String imageUrl = company.getCoCompany_photo_url();
            s3Service.deleteImageFromS3(imageUrl);
            companyRepository.deleteById(company_index);
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_COMPANY);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    // 공지사항만 보기
    @Override
    @Transactional
    public List<Notice> getNotice() {
        List<Notice> NoticeList = noticeRepository.findAll();
        return NoticeList;
    }

    // 해당 강사의 공지사항 보기..? 왜 보는거지 =.=
    @Override
    @Transactional
    public List<Notice> getNoticeList(Long lecturer_index) {
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturer_index);
        Lecturer lecturer = optionalLecturer.get();
        return lecturer.getNoticeList();
    }

    @Override
    @Transactional
    public void registerNotice(Long lecturer_index, NoticeRegisterDto noticeRegisterDto) {
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(lecturer_index);
        if(optionalLecturer.isPresent()){
            Lecturer lecturer = optionalLecturer.get();
            Notice newnotice = Notice.builder()
                    .notice_title(noticeRegisterDto.getNotice_title())
                    .lecturer_name(noticeRegisterDto.getLecturer_name())
                    .notice_content(noticeRegisterDto.getNotice_content())
                    .build();
            noticeRepository.save(newnotice);
            lecturer.addNoticeList(newnotice);
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_FIND_LECTURER);
        }
    }

    @Override
    @Transactional
    public void modifyNotice(Long notice_index, NoticeRegisterDto noticeRegisterDto) {
        Optional<Notice> optionalNotice = noticeRepository.findById(notice_index);
        if (optionalNotice.isPresent()){
            Notice notice = optionalNotice.get();
            notice.NoticeUpdate(noticeRegisterDto.getNotice_title(), noticeRegisterDto.getLecturer_name(), noticeRegisterDto.getNotice_content());
            Lecturer lecturer = notice.getLecturer();
            noticeRepository.save(notice);
            lecturer.addNoticeList(notice);
        }else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_NOTICE);
        }
    }

    @Override
    @Transactional
    public void deleteNotice(Long notice_index) {
        Optional<Notice> optionalNotice = noticeRepository.findById(notice_index);
        if (optionalNotice.isPresent()){
            Notice notice = optionalNotice.get();
            Lecturer lecturer = notice.getLecturer();
            noticeRepository.deleteById(notice_index);
            lecturer.removeNoticeList(notice);
        }else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_NOTICE);
        }
    }

    /////////////////////////////////////////////////////////////////
    @Override
    @Transactional
    public List<Lecturer> getLecturer() {
        List<Lecturer> LecturerList = lecturerRepository.findAll();
        return LecturerList;
    }

    @Override
    @Transactional
    public void registerLecturer(LecturerRegisterDto lecturerRegisterDto) {
        Lecturer lecturer = new Lecturer(lecturerRegisterDto.getLecturer_photo(), lecturerRegisterDto.getLecturer_name(), lecturerRegisterDto.getLecturer_career(), lecturerRegisterDto.getLecturer_address());
        Lecturer newlecturer = Lecturer.builder()
                .lecturer_photo(lecturer.getLecturer_photo())
                .lecturer_name(lecturer.getLecturer_name())
                .lecturer_career(lecturer.getLecturer_career())
                .lecturer_address(lecturer.getLecturer_address())
                .build();
        lecturerRepository.save(newlecturer);
    }

    @Override
    @Transactional
    public void modifyLecturer(Long lecturer_index, LecturerRegisterDto lecturerRegisterDto) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(lecturer_index);
        lecturer.get().LecturerUpdate(lecturerRegisterDto.getLecturer_photo(), lecturerRegisterDto.getLecturer_name(), lecturerRegisterDto.getLecturer_career(), lecturerRegisterDto.getLecturer_address());
        lecturerRepository.save(lecturer.get());
        if (!lecturerRepository.existsById(lecturer_index)) {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_LECTURER);
        }
    }

    @Override
    @Transactional
    public void deleteLecturer(Long lecturer_index) {
        Optional<Lecturer> Lecturer = lecturerRepository.findById(lecturer_index);
        if (Lecturer.isPresent()) {
            lecturerRepository.deleteById(lecturer_index);
        } else {
            throw new CompanyNotFindException(ExceptionList.NOT_EXISTENT_LECTURER);
        }
    }
}
