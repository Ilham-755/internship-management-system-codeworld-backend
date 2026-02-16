package com.codeworld.backendproject.service;

import org.springframework.stereotype.Service;

@Service
public interface ExportService {
    byte[] exportAllApplicationsExcel();

    byte[] exportAcceptedApplicationsExcel();

    byte[] exportApplicationsWord();

}
//   public static byte[] exportApplicationsWord(){
//        List<ApplicationEntity> apps = ApplicationService;
//
//        try (XWPFDocument doc = new XWPFDocument();
//             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//
//            XWPFParagraph title = doc.createParagraph();
//            title.setAlignment(ParagraphAlignment.CENTER);
//            XWPFRun run = title.createRun();
//            run.setBold(true);
//            run.setFontSize(14);
//            run.setText("Applications");
//
//            doc.createParagraph();
//
//            int cols = 8;
//            XWPFTable table = doc.createTable(apps.size() + 1, cols);
//            table.setWidth("100%");
//
//            setCellText(table.getRow(0).getCell(0), "ID", true);
//            setCellText(table.getRow(0).getCell(1), "Ad", true);
//            setCellText(table.getRow(0).getCell(2), "Soyad", true);
//            setCellText(table.getRow(0).getCell(3), "Email", true);
//            setCellText(table.getRow(0).getCell(4), "Telefon", true);
//            setCellText(table.getRow(0).getCell(5), "Sahə", true);
//            setCellText(table.getRow(0).getCell(6), "Təhsil", true);
//            setCellText(table.getRow(0).getCell(7), "Status", true);
//
//            for (int i = 0; i < apps.size(); i++) {
//                ApplicationEntity a = apps.get(i);
//                XWPFTableRow row = table.getRow(i + 1);
//            }
//
//            doc.write(out);
//            return out.toByteArray();
//        } catch (Exception e) {
//            throw new RuntimeException("Word export xətası:", e);
//        }
//    }
//
//    private static String safe(String s) {
//        return s == null ? "" : s;
//    }
//
//    private static void setCellText(XWPFTableCell cell, String text, boolean bold) {
//        cell.removeParagraph(0);
//
//        XWPFParagraph p = cell.addParagraph();
//        XWPFRun r = p.createRun();
//        r.setBold(bold);
//        r.setText(text == null ? "" : text);
//    }
//
//}
