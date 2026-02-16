package com.codeworld.backendproject.dto.proqram;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ProgramCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String ProgramTypeEnumtype;

    @NotBlank
    @Min(1)
    private Integer programNumber;

    @NotBlank
    @Min(1)
    @Max(12)
    private Integer startMonth;

    @NotBlank
    @Min(1970)
    @Max(2100)
    private Integer startYear;

    private LocalDate endDate;
}
