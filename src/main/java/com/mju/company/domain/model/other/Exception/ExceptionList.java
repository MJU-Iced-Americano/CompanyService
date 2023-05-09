package com.mju.company.domain.model.other.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.management.loading.MLetContent;

@RequiredArgsConstructor
@Getter
public enum ExceptionList {

    UNKNOWN(-9999, "알 수 없는 오류가 발생하였습니다."),
    NOT_EXISTENT_COMPANY(-5005, "존재하지 않는 협력사 목록입니다."),
    NOT_EXISTENT_FIND_COMPANY(-5004, "협렵사 목록이 없습니다."),
    NOT_EXISTENT_NOTICE(-5005, "존재하지 않는 공지사항 목록입니다."),
    NOT_EXISTENT_LECTURER(-5005, "존재하지 않는 강사진 목록입니다."),

    NOT_FIND_LECTURER(-6001, "찾을 수 없는 강사입니다.");
    private final int code;
    private final String message;
}
