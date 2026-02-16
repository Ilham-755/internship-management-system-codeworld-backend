package com.codeworld.backendproject.service;

import com.codeworld.backendproject.entity.ProgramEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProgramService {
    ProgramEntity getActiveProgram();
    List<ProgramEntity> getAllProgram();
}
