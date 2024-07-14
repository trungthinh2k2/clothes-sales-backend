package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.utils.EmailDetails;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details) throws MessagingException, IOException;
    void sendHtmlMail(EmailDetails details) throws MessagingException;
}
