package com.mju.company.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class Notice {

    @Id
    @Column(name = "notice_index")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "notice_title")
    private String notice_title;
    @Column(name = "lecturer_name")
    private String lecturer_name;
    @Column(name = "notice_date")
    private LocalDateTime notice_date;
    @Column(name = "notice_content")
    private String notice_content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lecturer_index")
    private Lecturer lecturer;

    public Notice() {

    }

    @PrePersist
    public void prePersist(){
        this.notice_date = LocalDateTime.now();
    }

    @Builder
    public Notice(String notice_title, String lecturer_name, LocalDateTime notice_date, String notice_content){
        this.notice_title = notice_title;
        this.lecturer_name = lecturer_name;
        this.notice_date = notice_date;
        this.notice_content = notice_content;
    }

    public Notice(String notice_title, String lecturer_name, String notice_content){
        this.notice_title = notice_title;
        this.lecturer_name = lecturer_name;
        this.notice_content = notice_content;
    }

    public void NoticeUpdate(String notice_title, String lecturer_name, String notice_content){
        this.notice_title = notice_title;
        this.lecturer_name = lecturer_name;
        this.notice_content = notice_content;
    }

    public void initialization(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

}