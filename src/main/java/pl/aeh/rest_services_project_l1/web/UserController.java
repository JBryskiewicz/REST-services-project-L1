package pl.aeh.rest_services_project_l1.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;
import pl.aeh.rest_services_project_l1.domain.user.AuthRequest;
import pl.aeh.rest_services_project_l1.service.user.AppUserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import pl.aeh.rest_services_project_l1.service.user.JwtService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController()
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final AppUserService appUserService;
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/check")
    public ResponseEntity<String> checkEndpoint() {
        return ResponseEntity.ok("ENDPOINT - PUBLIC");
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAppUser(@RequestBody AppUser user) {
        return ResponseEntity.ok(this.appUserService.createUser(user));
    }

    @PostMapping("/generateToken")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            Optional<AppUser> maybeUser = this.appUserService.getUserByEmail(authRequest.getUsername());
            String token = jwtService.generateToken(authRequest.getUsername());
            Map<String, String> response = new HashMap<>();

            if (maybeUser.isPresent()) {
                response.put("token", token);
                response.put("userId", maybeUser.get().getId().toString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
