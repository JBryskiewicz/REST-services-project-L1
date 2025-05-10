package pl.aeh.rest_services_project_l1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    AppUser getAppUserByUsername(String username);
}
