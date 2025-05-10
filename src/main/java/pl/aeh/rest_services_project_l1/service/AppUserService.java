package pl.aeh.rest_services_project_l1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.aeh.rest_services_project_l1.repository.AppUserRepository;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser getUser(String username) {
        return appUserRepository.getAppUserByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.getAppUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities("USER")
                .build();

    }
}
