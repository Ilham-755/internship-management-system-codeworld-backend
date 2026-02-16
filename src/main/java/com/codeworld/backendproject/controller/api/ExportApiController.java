package com.codeworld.backendproject.controller.api;

import com.codeworld.backendproject.service.ExportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/export")
public class ExportApiController {

    @GetMapping
    public Map<String, String> list() {
        return Map.of(
                "excelAll", "/api/export/excel/all",
                "excelAccepted", "/api/export/excel/accepted",
                "word", "/api/export/word"
        );
    }

    private final ExportService exportService;

    public ExportApiController(ExportService exportService) {
        this.exportService = exportService;
    }
    @GetMapping("/excel/all")
    public ResponseEntity<byte[]> exportExcelAll() {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Application_sheet.xlsx")
                .body(exportService.exportAllApplicationsExcel());
    }
    @GetMapping("/excel/accepted")
    public ResponseEntity<byte[]> exportExcelAccepted() {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Accepted_applications.xlsx")
                .body(exportService.exportAcceptedApplicationsExcel());
    }
    @GetMapping("/word")
    public ResponseEntity<byte[]> exportWord() {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Applications.docx")
                .body(exportService.exportApplicationsWord());

    }
//    @GetMapping("/word")
//    public ResponseEntity<byte[]> exportWord() {
//        return ResponseEntity.ok()
//                .header("Content-Disposition", "attachment; filename=Applications.docx")
//                .header("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
//                .body(ExportService.exportApplicationsWord());
    }

