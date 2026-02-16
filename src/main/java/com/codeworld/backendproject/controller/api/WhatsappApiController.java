package com.codeworld.backendproject.controller.api;

import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/whatsapp")
public class WhatsappApiController {
    private final ApplicationService applicationService;

    public WhatsappApiController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWhatsappLink(@PathVariable Long id) {
        ApplicationEntity app = applicationService.getAppById(id);

//        String phone = app.getPhone();
//        phone = phone.replaceAll("\\D", "");
//
//        if (phone.startsWith("0")) {
//            phone = "+994" + phone.substring(1);
//        }
//        String message = "Salam " + app.getName() +
//                ", Code World müraciətinizlə bağlı sizinlə əlaqə saxlayırıq.";
//        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
//        return "https://wa.me/" + phone + "?text=" + encodedMessage;


        String myPhone = "994519895227";

        String message = "Salam, mən " + app.getName() + " "
                + app.getSurname()
                + ". Code World müraciətim barədə əlaqə saxlamaq istəyirəm.";

        String encodedMessage =
                URLEncoder.encode(message, StandardCharsets.UTF_8);

        String url = "https://wa.me/" + myPhone + "?text=" + encodedMessage;

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
