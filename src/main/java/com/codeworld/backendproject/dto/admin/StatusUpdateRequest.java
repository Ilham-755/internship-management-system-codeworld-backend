package com.codeworld.backendproject.dto.admin;

import com.codeworld.backendproject.entity.enums.ApplicationStatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StatusUpdateRequest {
    @NotNull
    private ApplicationStatusEnum status;

    @Size(max = 2000)
    private String adminNote;
}
