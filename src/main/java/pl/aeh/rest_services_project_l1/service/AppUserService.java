package pl.aeh.rest_services_project_l1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.aeh.rest_services_project_l1.AppUserRepository;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;


    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser getUser(String email) {
        return appUserRepository.getAppUserByEmail(email);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.getAppUserByEmail(email);
        if (appUser == null) {
            throw new UsernameNotFoundException(email);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities("USER")
                .build();

    }
}
