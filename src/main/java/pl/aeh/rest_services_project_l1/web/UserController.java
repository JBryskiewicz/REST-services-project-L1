package pl.aeh.rest_services_project_l1.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;
import pl.aeh.rest_services_project_l1.domain.user.AuthRequest;
import pl.aeh.rest_services_project_l1.service.user.AppUserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.aeh.rest_services_project_l1.service.user.JwtService;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final AppUserService appUserService;
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/check")
    public String checkEndpoint() {
        return "Endpoint - Public";
    }

    @PostMapping("/register")
    public String createAppUser(@RequestBody AppUser user) {
        return this.appUserService.createUser(user);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
