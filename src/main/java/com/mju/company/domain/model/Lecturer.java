package com.mju.company.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "lecturer")
public class Lecturer {

    //career, lecture, notice, address
    @jakarta.persistence.Id
    @Id
    @Column(name = "lecturer_index")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "lecturer_photo")
    private String lecturer_photo;
    @Column(name = "lecturer_name")
    private String lecturer_name;
    @Column(name = "lecturer_career")
    private String lecturer_career;
    @Column(name = "lecturer_address")
    private String lecturer_address;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notice> noticeList = new ArrayList<>();

    @Builder
    public Lecturer(String lecturer_photo, String lecturer_name, String lecturer_career, String lecturer_address){
        this.lecturer_photo = lecturer_photo;
        this.lecturer_name = lecturer_name;
        this.lecturer_career = lecturer_career;
        this.lecturer_address = lecturer_address;
    }

    public Lecturer() {

    }

    public void LecturerUpdate(String lecturer_photo, String lecturer_name, String lecturer_career, String lecturer_address){
        this.lecturer_photo = lecturer_photo;
        this.lecturer_name = lecturer_name;
        this.lecturer_career = lecturer_career;
        this.lecturer_address = lecturer_address;
    }

    public String getLecturer_photo() {
        return lecturer_photo;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public List<Notice> getNoticeList() {
        return this.noticeList;
    }

    public void addNoticeList(Notice notice) {
        this.noticeList.add(notice);
        notice.initialization(this);
    }

    public void removeNoticeList(Notice notice) {
        this.noticeList.remove(notice);
        notice.initialization(null);
    }
}
