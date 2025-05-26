package pl.aeh.rest_services_project_l1.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public AppUserDetails(AppUser user) {
        this.email = user.getEmail(); // Use email as username
        this.password = user.getPassword();
        this.authorities = Stream.of(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
