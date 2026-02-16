package com.codeworld.backendproject.exception;

import com.codeworld.backendproject.entity.enums.EducationTypeEnum;

public class DuplicateApplicationException extends Exception {

//    public DuplicateApplicationException(String message) {
//        super(message);
//    }
    public DuplicateApplicationException(String message) {

        super("Siz artıq bu proqram üzrə qeydiyyatdan keçmisiniz");
    }
}
