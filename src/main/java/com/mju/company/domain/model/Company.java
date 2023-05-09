package com.mju.company.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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

    @Builder
    public Company(String CoCompany_name, String CoCompany_url, String CoCompany_photo_url) {
        this.CoCompany_name = CoCompany_name;
        this.CoCompany_url = CoCompany_url;
        this.CoCompany_photo_url = CoCompany_photo_url;
    }

    public Company() {

    }

    public void CompanyUpdate(String CoCompany_name, String CoCompany_url, String CoCompany_photo_url){
        this.CoCompany_name = CoCompany_name;
        this.CoCompany_url = CoCompany_url;
        this.CoCompany_photo_url = CoCompany_photo_url;
    }
}