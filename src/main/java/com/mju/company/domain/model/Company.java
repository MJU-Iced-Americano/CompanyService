package com.mju.company.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company{

    @Id
    @Column(name = "company_index")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "co_company_name")
    private String CoCompany_name;
    @Column(name = "co_company_url")
    private String CoCompany_url;
    @Column(name = "co_company_photo_url")
    private String CoCompany_photo_url;

//    @Builder
//    public Company(String CoCompany_name, String CoCompany_url) {
//        this.CoCompany_name = CoCompany_name;
//        this.CoCompany_url = CoCompany_url;
//    }

    public Company() {

    }

    @Builder
    public Company(String CoCompany_name, String CoCompany_url, String CoCompany_photo_url) {
        this.CoCompany_name = CoCompany_name;
        this.CoCompany_url = CoCompany_url;
        this.CoCompany_photo_url = CoCompany_photo_url;
    }


    public void CompanyUpdate(String CoCompany_name, String CoCompany_url, String CoCompany_photo_url){
        this.CoCompany_name = CoCompany_name;
        this.CoCompany_url = CoCompany_url;
        this.CoCompany_photo_url = CoCompany_photo_url;
    }

//    public void addImage(String imageUrl) {
//
//        CompanyImage questionImage = new CompanyImage(imageUrl, this);
//        this.questionImageList.add(questionImage);
//    }
//
//    public void updateImages(List<String> imageUrls) {
//        this.questionImageList.clear();
//        for (String imageUrl : imageUrls) {
//            addImage(imageUrl);
//        }
//    }
//    public void removeImage(QuestionImage questionImage) {
//        this.questionImageList.remove(questionImage);
//        questionImage.initialization();
//    }
}