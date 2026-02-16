package com.codeworld.backendproject.service.impl;

import com.codeworld.backendproject.dto.application.ApplicationCreateRequest;
import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.entity.ProgramEntity;
import com.codeworld.backendproject.entity.enums.ApplicationStatusEnum;
import com.codeworld.backendproject.exception.DuplicateApplicationException;
import com.codeworld.backendproject.exception.NotFoundException;
import com.codeworld.backendproject.repository.ApplicationRepository;
import com.codeworld.backendproject.repository.ProgramRepository;
import com.codeworld.backendproject.service.ApplicationService;
import com.codeworld.backendproject.service.ProgramService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ProgramService programService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ProgramService programService) {
        this.applicationRepository = applicationRepository;
        this.programService = programService;
    }
    @Override
    public ApplicationEntity createApp(ApplicationCreateRequest request) {
        ProgramEntity activeProgram= programService.getActiveProgram();

        boolean exists=applicationRepository.existsByProgramAndEmailAndPhone(
                activeProgram,
                request.getEmail(),
                request.getPhoneNumber()
        );
        if(exists){
            try {
                throw new DuplicateApplicationException("Siz artıq bu proqram üzrə qeydiyyatdan keçmisiniz");
            } catch (DuplicateApplicationException e) {
                throw new RuntimeException(e);
            }
        }
        ApplicationEntity app = new ApplicationEntity();
        app.setProgram(activeProgram);
        app.setCreatedAtTime(LocalDateTime.now());
        app.setStatus(ApplicationStatusEnum.PENDING);
        app.setApplicationCode("CW-INT_"+System.currentTimeMillis());

        return applicationRepository.save(app);
    }

    @Override
    public java.util.List<ApplicationEntity> getAllProg() {

        return applicationRepository.findAll();
    }

    @Override
    public ApplicationEntity getAppById(long id) {
        return applicationRepository.findById(id).orElseThrow(()-> new NotFoundException("Müraciət tapılmadı: id="+ id));
    }
    public ApplicationEntity getByApplicationCode(String code) {
        return applicationRepository.findByApplicationCode(code)
                .orElseThrow(() -> new NotFoundException("Application tapılmadı: " + code));
    }
}
