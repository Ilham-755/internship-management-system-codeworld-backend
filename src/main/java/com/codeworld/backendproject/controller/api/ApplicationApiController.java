package com.codeworld.backendproject.controller.api;

import com.codeworld.backendproject.dto.application.ApplicationCreateRequest;
import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/applications")
public class ApplicationApiController {
    private final ApplicationService applicationService;
    public ApplicationApiController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @PostMapping
    public ApplicationEntity createApplication(@Valid @RequestBody ApplicationCreateRequest request) {
        return applicationService.createApp(request);
    }
}
