package com.codeworld.backendproject.controller.api;

import com.codeworld.backendproject.dto.admin.DashboardResponse;
import com.codeworld.backendproject.dto.admin.StatusUpdateRequest;
import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.repository.ApplicationRepository;
import com.codeworld.backendproject.service.ApplicationService;
import com.codeworld.backendproject.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminApiController {
    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;
    private final ProgramService programService;

    public AdminApiController(ApplicationService applicationService,
                              ApplicationRepository applicationRepository,
                              ProgramService programService) {
        this.applicationService = applicationService;
        this.applicationRepository = applicationRepository;
        this.programService = programService;

    }
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        DashboardResponse response = new DashboardResponse();
        response.setTotalApplications(applicationRepository.count());
        try {
            var activeProgram = programService.getActiveProgram();

            response.setHasActiveProgram(true);
            response.setActiveProgramName(activeProgram.getName());
            response.setActiveProgramNumber(activeProgram.getProgramNumber());
            response.setActiveProgramType(activeProgram.getType());
            response.setActiveProgramStartMonth(String.valueOf(activeProgram.getStartMonth()));
            response.setActiveProgramStartYear(String.valueOf(activeProgram.getStartYear()));

            response.setActiveProgramId(
                    applicationRepository.countByProgramId(activeProgram.getId())
            );

        } catch (Exception e) {
            response.setHasActiveProgram(false);
            response.setActiveProgramId(0);
        }
        return response;
    }

    @GetMapping("/applications")
    public List<ApplicationEntity> getApplications() {

        return applicationService.getAllProg();
    }
    @PatchMapping("/applications/{id}/status")
    public ApplicationEntity addApplication(@PathVariable Long id,
                                            @RequestBody ApplicationEntity applicationEntity) {
        ApplicationEntity app = applicationService.getAppById(id);

        app.setStatus(applicationEntity.getStatus());
        app.setAdminNote(applicationEntity.getAdminNote());
//        app.setStatus(java.time.LocalDateTime.now());

        return applicationRepository.save(app);

    }
//@PatchMapping("/applications/{id}/status")
//public ApplicationEntity updateStatus(@PathVariable Long id,
//                                      @RequestBody StatusUpdateRequest request) {
//
//    ApplicationEntity app = applicationService.getAppById(id);
//
//    app.setStatus(request.getStatus());
//    app.setAdminNote(request.getAdminNote());
////    app.setStatus(java.time.LocalDateTime.now());
//
//    return applicationRepository.save(app);
//}

}
