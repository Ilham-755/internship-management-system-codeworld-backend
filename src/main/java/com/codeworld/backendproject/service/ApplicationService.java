package com.codeworld.backendproject.service;

import com.codeworld.backendproject.dto.application.ApplicationCreateRequest;
import com.codeworld.backendproject.entity.ApplicationEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ApplicationService {
    ApplicationEntity createApp(ApplicationCreateRequest request);
    List<ApplicationEntity> getAllProg();
    ApplicationEntity getAppById(long id);

}
