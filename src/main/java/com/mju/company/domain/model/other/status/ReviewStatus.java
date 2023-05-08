package com.mju.company.domain.model.other.status;

public enum ReviewStatus {
    UNDECLARE("미신고"),
    DECLARE("신고");

    private final String value;

    ReviewStatus(String value){
        this.value = value;
    }
}
