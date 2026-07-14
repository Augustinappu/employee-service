package employee_service.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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

    private static final long RESET_TOKEN_EXPIRY_MINUTES = 15;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            EmailService emailService,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    public User register(RegisterRequest request) {

        String cleanEmail =
                request.getEmail().trim().toLowerCase();

        String cleanUsername =
                request.getUsername().trim();

        User user = new User();

        user.setUsername(cleanUsername);
        user.setEmail(cleanEmail);
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public String forgotPassword(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Email is required"
            );
        }

        String cleanEmail =
                email.trim().toLowerCase();

        Optional<User> userOptional =
                userRepository.findByEmailIgnoreCase(
                        cleanEmail
                );

        if (userOptional.isEmpty()) {
            return "If the email is registered, a reset link has been sent.";
        }

        User user = userOptional.get();

        String token =
                UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setResetTokenGeneratedTime(
                LocalDateTime.now()
        );

        userRepository.save(user);

        emailService.sendResetTokenEmail(
                user.getEmail(),
                token
        );

        return "If the email is registered, a reset link has been sent.";
    }

    public String resetPassword(
            String token,
            String newPassword) {

        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Reset token is required"
            );
        }

        if (newPassword == null ||
                newPassword.length() < 8) {

            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters"
            );
        }

        User user =
                userRepository.findByResetToken(token)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Invalid or already-used reset link"
                                )
                        );

        LocalDateTime generatedTime =
                user.getResetTokenGeneratedTime();

        if (generatedTime == null) {
            clearResetToken(user);

            throw new IllegalArgumentException(
                    "Invalid reset link"
            );
        }

        LocalDateTime expiryTime =
                generatedTime.plusMinutes(
                        RESET_TOKEN_EXPIRY_MINUTES
                );

        if (LocalDateTime.now().isAfter(expiryTime)) {
            clearResetToken(user);

            throw new IllegalArgumentException(
                    "Reset link has expired. Request a new one."
            );
        }

        user.setPassword(
                passwordEncoder.encode(newPassword)
        );

        user.setResetToken(null);
        user.setResetTokenGeneratedTime(null);

        userRepository.save(user);

        return "Password reset successfully";
    }

    private void clearResetToken(User user) {

        user.setResetToken(null);
        user.setResetTokenGeneratedTime(null);

        userRepository.save(user);
    }

    public String login(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByUsername(
                        request.getUsername()
                );

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (passwordMatches) {
            return jwtService.generateToken(
                    user.getUsername()
            );
        }

        return "Invalid Password";
    }
}