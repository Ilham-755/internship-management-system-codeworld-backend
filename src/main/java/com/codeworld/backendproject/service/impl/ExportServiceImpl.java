package com.codeworld.backendproject.service.impl;
import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.entity.enums.ApplicationStatusEnum;
import com.codeworld.backendproject.repository.ApplicationRepository;
import com.codeworld.backendproject.service.ExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;



@Service
public class ExportServiceImpl implements ExportService {

    private static final String[] HEADERS ={
            "No","ApplicationCode","Name","Surname","PhoneNumber","EmailAddress",
            "Field","Education","Status","Program"
    };

   private final ApplicationRepository  applicationRepository;

   public ExportServiceImpl(ApplicationRepository applicationRepository) {
       this.applicationRepository = applicationRepository;
   }

    @Override
    public byte[] exportAllApplicationsExcel() {
        return buildExcel(applicationRepository.findAll());
    }

    @Override
    public byte[] exportAcceptedApplicationsExcel() {
        List<ApplicationEntity> accepted= applicationRepository.findAll().stream().
                filter(a-> a.getStatus()==ApplicationStatusEnum.ACCEPTED).
                toList();
        return buildExcel(accepted);
    }

    @Override
    public byte[] exportApplicationsWord() {
        return buildWord(applicationRepository.findAll());
    }

    private byte[] buildExcel(List<ApplicationEntity> lists) {
       try (Workbook wb= new XSSFWorkbook(); ByteArrayOutputStream out= new ByteArrayOutputStream()){
         Sheet sheet = wb.createSheet("Applications");

         writesRow(sheet.createRow(0),HEADERS);

         int rowIndex=1;
         for(int i=0; i<lists.size(); i++) {
             ApplicationEntity application = lists.get(i);

             String[] rows={
                     String.valueOf(i+1),
                     safe(application.getEmail()),
                     safe(application.getApplicationCode()),
                     safe(application.getName()),
                     safe(application.getSurname()),
                     safe(application.getPhone()),
                     enumText(application.getStatus()),
                     enumText(application.getEducationType()),
                     enumText(application.getField()),
                     application.getProgram()==null ? "": safe(application.getProgram().getName())

             };
             writesRow(sheet.createRow(rowIndex++), rows);
         }
         for(int e=0; e<HEADERS.length; e++) {
             sheet.autoSizeColumn(e);
         }
         wb.write(out);
         return out.toByteArray();
       }catch (Exception e){
           throw new RuntimeException("Excel export xətası:"+e.getMessage());

       }
    }
    private byte[] buildWord(List<ApplicationEntity> lists) {
       try(XWPFDocument doc=new XWPFDocument(); ByteArrayOutputStream output=new ByteArrayOutputStream()){
           addTitleName(doc,"Code World Təcrübə və Təqaüd Proqramına qeydiyyatdan keçənlər");
       for (int a=0; a<lists.size(); a++) {
           ApplicationEntity application = lists.get(a);

           addBold(doc,(a+1)+ ")"+ safe(application.getName())+" "+ safe(application.getSurname()));
       addLine(doc,List.of(
               "ApplikasiyaKodu:"+safe(application.getApplicationCode()),
               "Email:"+ safe(application.getEmail()),
               "Sahə:"+ safe(application.getField()),
               "Təhsil tipi:"+ enumText(application.getEducationType()),
               "Telefon:"+ safe(application.getPhone()),
               "Status:"+ enumText(application.getStatus()),
               "Motivasiya:"+ safe(application.getMotivation()),
               "Program: " + (application.getProgram() == null ? "" : safe(application.
                       getProgram().getName()
               ))));
                doc.createParagraph();
       }
       doc.write(output);
       return output.toByteArray();
       }catch(Exception a){
           throw new RuntimeException("Word export xətası:"+a.getMessage());
       }
    }
    private void writesRow(Row row, String[] values) {
        for (int i = 0; i < values.length; i++) {
            row.createCell(i).setCellValue(values[i]);
        }
    }
    private void addTitleName(XWPFDocument document, String text) {
       XWPFParagraph p= document.createParagraph();
       p.setAlignment(ParagraphAlignment.CENTER);
       XWPFRun run=p.createRun();
       run.setBold(true);
       run.setFontSize(14);
       run.setText(text);
       document.createParagraph();
    }
    private void addBold(XWPFDocument docs, String text){
       XWPFParagraph p= docs.createParagraph();
        XWPFRun run=p.createRun();
        run.setBold(true);
        run.setText(text);
    }
    private void addLine(XWPFDocument document, List<String> lines){
        XWPFParagraph p= document.createParagraph();
        XWPFRun run=p.createRun();
        for(int i=0; i<lines.size(); i++) {
            run.setText(lines.get(i));
            if(i<lines.size()-1) run.addBreak();
        }
    }
    private String enumText(Object v) {
        if (v == null) return "";
        if (v instanceof Enum<?> en) return en.name();
        return String.valueOf(v);
    }

    //    private String enumText(Enum<?> e) {
//
//       return e == null ? "" : e.name();
//    }
    private String safe(String s) {
       return s==null ? "": s;

    }
}

