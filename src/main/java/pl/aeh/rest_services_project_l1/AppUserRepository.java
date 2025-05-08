package pl.aeh.rest_services_project_l1;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    AppUser getAppUserByEmail(String email);

    AppUser getAppUserByUsername(String username);
}
