package employee_service.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import employee_service.auth.JwtService;
import employee_service.dto.LoginRequest;
import employee_service.dto.RegisterRequest;
import employee_service.user.Role;
import employee_service.user.User;
import employee_service.user.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService;
   
    public User register(RegisterRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public String forgotPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setResetTokenGeneratedTime(LocalDateTime.now());

        userRepository.save(user);

        emailService.sendResetTokenEmail(email, token);

        return "Reset token sent to email";
    }

    public String resetPassword(String token, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(token);

        if (userOptional.isEmpty()) {
            return "Invalid token";
        }

        User user = userOptional.get();

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenGeneratedTime(null);

        userRepository.save(user);

        return "Password reset successfully";
    }

    public String login(LoginRequest request) {
        Optional<User> userOptional =
                userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return jwtService.generateToken(user.getUsername());
        }

        return "Invalid Password";
    }
}