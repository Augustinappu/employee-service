package employee_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee_service.dto.UserProfileUpdateDTO;
import employee_service.entity.UserProfileUpdate;
import employee_service.repository.UserProfileUpdateRepository;


@Service
public class UserProfileUpdateService {

    @Autowired
    private UserProfileUpdateRepository userProfileRepo;

    public UserProfileUpdateDTO updateProfile(UserProfileUpdateDTO dto) {

        UserProfileUpdate profile = userProfileRepo
                .findByUserOfficialEmail(dto.getUserOfficialEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUserName(dto.getUserName());
        profile.setUserOfficialEmail(dto.getUserOfficialEmail());
        profile.setUserPersonalEmail(dto.getUserPersonalEmail());
        profile.setDepartment(dto.getDepartment());
        profile.setDesignation(dto.getDesignation());
        profile.setJobLevel(dto.getJobLevel());
        profile.setOrganizationName(dto.getOrganizationName());
        profile.setActive(dto.isActive());

        userProfileRepo.save(profile);

        return toDTO(profile);
    }

    public UserProfileUpdateDTO updateProfileByEmail(String email, UserProfileUpdateDTO dto) {

        UserProfileUpdate profile = userProfileRepo
                .findByUserOfficialEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUserName(dto.getUserName());
        profile.setUserOfficialEmail(dto.getUserOfficialEmail());
        profile.setUserPersonalEmail(dto.getUserPersonalEmail());
        profile.setDepartment(dto.getDepartment());
        profile.setDesignation(dto.getDesignation());
        profile.setJobLevel(dto.getJobLevel());
        profile.setOrganizationName(dto.getOrganizationName());
        profile.setActive(dto.isActive());
        

        userProfileRepo.save(profile);

        return toDTO(profile);
    }

    public List<UserProfileUpdateDTO> getAllProfiles() {
        return userProfileRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserProfileUpdateDTO getProfileByEmail(String email) {

        UserProfileUpdate profile = userProfileRepo
                .findByUserOfficialEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toDTO(profile);
    }

    public void deleteProfile(Long id) {
        userProfileRepo.deleteById(id);
    }

    private UserProfileUpdateDTO toDTO(UserProfileUpdate profile) {

        UserProfileUpdateDTO dto = new UserProfileUpdateDTO();

        dto.setUserOfficialEmail(profile.getUserOfficialEmail());
        dto.setUserPersonalEmail(profile.getUserPersonalEmail());
        dto.setUserName(profile.getUserName());
        dto.setDepartment(profile.getDepartment());
        dto.setDesignation(profile.getDesignation());
        dto.setJobLevel(profile.getJobLevel());
        dto.setOrganizationName(profile.getOrganizationName());
        dto.setActive(profile.isActive());
        dto.setId(profile.getId());

        return dto;
    }
    public UserProfileUpdateDTO createProfile(UserProfileUpdateDTO dto) {

        UserProfileUpdate profile = new UserProfileUpdate();

        profile.setUserOfficialEmail(dto.getUserOfficialEmail());
        profile.setUserPersonalEmail(dto.getUserPersonalEmail());
        profile.setUserName(dto.getUserName());
        profile.setDepartment(dto.getDepartment());
        profile.setDesignation(dto.getDesignation());
        profile.setJobLevel(dto.getJobLevel());
        profile.setOrganizationName(dto.getOrganizationName());
        profile.setActive(dto.isActive());

        userProfileRepo.save(profile);

        return toDTO(profile);
    }
}