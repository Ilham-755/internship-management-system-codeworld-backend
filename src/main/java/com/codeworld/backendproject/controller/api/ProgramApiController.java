package com.codeworld.backendproject.controller.api;

import com.codeworld.backendproject.entity.ProgramEntity;
import com.codeworld.backendproject.service.ProgramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/programs")
public class ProgramApiController {
    private final ProgramService programService;

    public ProgramApiController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public List<ProgramEntity> getAllProg() {
        return programService.getAllProgram();
    }
    @GetMapping("/active")
    public ProgramEntity getActiveProgram() {
        return programService.getActiveProgram();
    }
}
