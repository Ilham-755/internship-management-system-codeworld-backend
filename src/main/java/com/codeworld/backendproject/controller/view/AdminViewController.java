package com.codeworld.backendproject.controller.view;

import com.codeworld.backendproject.dto.admin.DashboardResponse;
import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.entity.ProgramEntity;
import com.codeworld.backendproject.repository.ApplicationRepository;
import com.codeworld.backendproject.service.ApplicationService;
import com.codeworld.backendproject.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view")
public class AdminViewController {

    private final ApplicationService applicationService;
    private final ProgramService programService;
    private final ApplicationRepository applicationRepository;

    public AdminViewController(ApplicationService applicationService,
                               ProgramService programService,
                               ApplicationRepository applicationRepository) {
        this.applicationService = applicationService;
        this.programService = programService;
        this.applicationRepository = applicationRepository;
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        ProgramEntity active=null;
        long totalnumber=applicationRepository.count();
        long activenumber=0;

        try {
            active= programService.getActiveProgram();
            totalnumber=applicationRepository.countByProgramId(Long.valueOf(active.getProgramNumber()));
        }catch (Exception ignored){

        }
        DashboardResponse d= new DashboardResponse();
        model.addAttribute("dash",d);
        model.addAttribute("activeprogram",active);
        return "dashboard";
    }
    @GetMapping("/applications")
    public String applications(Model model){
        List<ApplicationEntity> list=applicationRepository.findAll();
        model.addAttribute("applications",list);
        return "applications";
    }
    @GetMapping("/applications/{id}")
    public String applicationDetails(@PathVariable Long id, Model model){
        ApplicationEntity app= applicationService.getAppById(id);
        model.addAttribute("application",app);
        return "application-details";

    }
}
