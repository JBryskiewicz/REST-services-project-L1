package pl.aeh.rest_services_project_l1.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.aeh.rest_services_project_l1.repository.AppUserRepository;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userDetail = repository.findByEmail(email); // Assuming 'email' is used as username

        return userDetail.map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public String createUser(AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        repository.save(user);
        return "User Added Successfully";
    }

    public Optional<AppUser> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
