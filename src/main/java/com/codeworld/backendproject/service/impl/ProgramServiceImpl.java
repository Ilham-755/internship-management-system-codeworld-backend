package com.codeworld.backendproject.service.impl;

import com.codeworld.backendproject.entity.ProgramEntity;
import com.codeworld.backendproject.exception.NoActiveProgramException;
import com.codeworld.backendproject.repository.ProgramRepository;
import com.codeworld.backendproject.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository ;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }
    @Override
    public ProgramEntity getActiveProgram() {
        return programRepository.findByActiveTrue()
                .orElseThrow(()-> new NoActiveProgramException("Aktiv proqram yoxdur.Qeydiyyat mümkün deyil!"));
    }
    @Override
    public List<ProgramEntity> getAllProgram() {
        return programRepository.findAll();
    }
}
