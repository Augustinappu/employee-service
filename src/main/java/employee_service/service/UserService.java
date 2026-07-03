package employee_service.service;


import employee_service.dto.RegisterRequest;
import employee_service.user.User;
import employee_service.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import employee_service.dto.LoginRequest;
import employee_service.auth.JwtService;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public User register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }
    public String login(LoginRequest request) {

        Optional<User> userOptional =
                userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        if (passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return jwtService.generateToken(user.getUsername());
        }

        return "Invalid Password";
    }
}