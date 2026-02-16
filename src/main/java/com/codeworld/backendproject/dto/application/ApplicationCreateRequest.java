package com.codeworld.backendproject.dto.application;

import com.codeworld.backendproject.entity.enums.ApplicationFieldEnum;
import com.codeworld.backendproject.entity.enums.EducationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApplicationCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Size(max = 2000)
    private String motivation;

    @NotNull
    private EducationTypeEnum educationType;

    @NotNull
    private ApplicationFieldEnum applicationField;

    @Size(max = 2000)
    private String feedback;

    private String cvPath;
}
