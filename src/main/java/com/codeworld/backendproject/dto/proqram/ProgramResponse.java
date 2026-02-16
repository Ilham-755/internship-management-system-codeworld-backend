package com.codeworld.backendproject.dto.proqram;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ProgramResponse {
    private Long id;
    private String name;
    private String ProgramTypeEnum;
    private Integer startMonth;
    private Integer startYear;
    private LocalDate endDate;
    private Boolean active;

}
