package employee_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetTokenEmail(
            String recipientEmail,
            String token) {

        String resetLink =
                frontendUrl
                        + "/reset-password?token="
                        + token;

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("HRMS Password Reset");

        message.setText(
                "Hello,\n\n"
                        + "Click the link below to reset your password:\n\n"
                        + resetLink
                        + "\n\nThis link expires in 15 minutes."
                        + "\n\nIf you did not request this, ignore this email."
        );

        mailSender.send(message);
    }
}