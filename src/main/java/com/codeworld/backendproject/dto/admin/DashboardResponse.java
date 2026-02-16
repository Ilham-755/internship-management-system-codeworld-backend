package com.codeworld.backendproject.dto.admin;

import com.codeworld.backendproject.entity.enums.ProgramTypeEnum;
import lombok.Data;

@Data
public class DashboardResponse {
    private long totalApplications;
    private long activeApplications;
    private long activeProgramId;
    private String activeProgramName;
    private ProgramTypeEnum activeProgramType;
    private Integer activeProgramNumber;
    private String activeProgramStartMonth;
    private String activeProgramStartYear;
    private Boolean hasActiveProgram;



}
