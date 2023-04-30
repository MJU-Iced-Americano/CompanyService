package com.mju.company.domain.model.other.Exception;

public class CompanyNotFindException extends RuntimeException{

    private final ExceptionList exceptionList;

    public CompanyNotFindException(ExceptionList exceptionList){
        super(exceptionList.getMessage());
        this.exceptionList = exceptionList;
    }

    public ExceptionList getExceptionList() {
        return exceptionList;
    }
}
