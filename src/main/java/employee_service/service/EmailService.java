package employee_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Forgot Password OTP");
        message.setText("Your OTP for password reset is: " + otp);

        mailSender.send(message);
    }
    public void sendResetTokenEmail(String toEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("augustinappu22@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Password Reset Token");
        message.setText("Use this token to reset your password:\n\n" + token);

        mailSender.send(message);
    }
}