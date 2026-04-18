package kz.enu.vtrestapi.controller;

import kz.enu.vtrestapi.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody Map<String, String> request) {
        String to = request.getOrDefault("to", "");
        String subject = request.getOrDefault("subject", "Test message");
        String text = request.getOrDefault("text", "Hello");
        try {
            emailService.sendEmail(to, subject, text);
            return ResponseEntity.ok("Email sent");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
}
