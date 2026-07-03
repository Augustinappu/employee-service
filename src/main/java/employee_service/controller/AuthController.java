package employee_service.controller;


import employee_service.dto.RegisterRequest;
import employee_service.service.UserService;
import employee_service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import employee_service.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return userService.login(request);

    }
}