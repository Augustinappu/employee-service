package employee_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee_service.dto.UserProfileUpdateDTO;
import employee_service.service.UserProfileUpdateService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/user_profile_update")
@CrossOrigin(origins = "*")
public class UserProfileUpdateController {

    @Autowired
    private UserProfileUpdateService userProfileUpdateService;

    @PutMapping("/updateProfile")
    public ResponseEntity<UserProfileUpdateDTO> updateProfile(
            @RequestBody UserProfileUpdateDTO profileDTO) {

        return ResponseEntity.ok(
                userProfileUpdateService.updateProfile(profileDTO));
    }

    @PutMapping("/updateProfile/{email}")
    public ResponseEntity<UserProfileUpdateDTO> updateProfileByEmail(
            @PathVariable String email,
            @RequestBody UserProfileUpdateDTO profileDTO) {

        return ResponseEntity.ok(
                userProfileUpdateService.updateProfileByEmail(email, profileDTO));
    }
    @PostMapping("/createProfile")
    public ResponseEntity<UserProfileUpdateDTO> createProfile(
            @RequestBody UserProfileUpdateDTO profileDTO) {

        return ResponseEntity.ok(
                userProfileUpdateService.createProfile(profileDTO));
    }

    @GetMapping("/allProfiles")
    public ResponseEntity<List<UserProfileUpdateDTO>> getAllProfiles() {

        return ResponseEntity.ok(
                userProfileUpdateService.getAllProfiles());
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserProfileUpdateDTO> getProfileByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                userProfileUpdateService.getProfileByEmail(email));
    }

    @DeleteMapping("/deleteProfile/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {

        userProfileUpdateService.deleteProfile(id);

        return ResponseEntity.ok("Profile deleted successfully");
    }
}