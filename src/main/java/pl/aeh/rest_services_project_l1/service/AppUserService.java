package pl.aeh.rest_services_project_l1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aeh.rest_services_project_l1.AppUserRepository;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUser getUser(String email) {
        return appUserRepository.getAppUserByEmail(email);
    }

    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }
}
