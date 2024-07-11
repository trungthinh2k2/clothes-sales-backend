package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.service.interfaces.EmailService;
import iuh.fit.salesappbackend.utils.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());

        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully...";
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setText(details.getMsgBody(), true);
        mimeMessageHelper.setSubject(details.getSubject());

        MultipartFile multipartFile = details.getAttachment();
        Path tempFile = Files.createTempFile("temp", multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
        FileSystemResource fileSystemResource= new FileSystemResource(new File(tempFile.toFile().getAbsolutePath()));
        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        javaMailSender.send(mimeMessage);
        return "Mail sent Successfully";
    }

    @Override
    public void sendHtmlMail(EmailDetails details) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setSubject(details.getSubject());
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        variables.put("msgBody", details.getMsgBody());
        context.setVariables(variables);
        String htmlContent = templateEngine.process("send-email", context);
        mimeMessageHelper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
    }
}
