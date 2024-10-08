package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.service.interfaces.EmailService;
import iuh.fit.salesappbackend.utils.EmailDetails;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestSendEmail {
    private final EmailService emailService;

    @PostMapping
    public String sendMail(@RequestBody EmailDetails emailDetails) {
        return emailService.sendSimpleMail(emailDetails);
    }

    @PostMapping("/attachment-file")
    public String sendMailWithAttachment(@ModelAttribute EmailDetails emailDetails)
            throws Exception {
        return emailService.sendMailWithAttachment(emailDetails);
    }

    @PostMapping("/html-mail")
    public String sendHtmlMail(@RequestBody EmailDetails emailDetails) throws MessagingException {
        emailService.sendHtmlMail(emailDetails);
        return "Mail Sent Successfully...";
    }

    @GetMapping
    public String test() {
        return "test token";
    }

    @GetMapping("/user")
    public String testUser() {
        return "test token role user";
    }

    @GetMapping("/admin")
    public String testAdmin() {
        return "test token role admin";
    }
}
