package com.codeworld.backendproject.dto.application;

import com.codeworld.backendproject.entity.enums.ApplicationStatusEnum;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ApplicationResponse {
    private Long  id;
    private String applicationCode;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String motivation;
    private String feedback;
    private String cvPath;
    private ApplicationStatusEnum status;
    private LocalDateTime createAtDate;
    private LocalDateTime updateAtDate;
    private String adminNotes;
    private String programName;
    private Long programId;

}
