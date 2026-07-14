package employee_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee_service.entity.UserProfileUpdate;

@Repository
public interface UserProfileUpdateRepository extends JpaRepository<UserProfileUpdate, Long> {

    Optional<UserProfileUpdate> findByUserOfficialEmail(String userOfficialEmail);

    Optional<UserProfileUpdate> findByUserPersonalEmail(String userPersonalEmail);
    

}