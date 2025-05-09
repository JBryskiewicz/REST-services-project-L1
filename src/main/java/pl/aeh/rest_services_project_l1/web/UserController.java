package pl.aeh.rest_services_project_l1.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.aeh.rest_services_project_l1.service.JwtService;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;
import pl.aeh.rest_services_project_l1.domain.user.AuthRequest;
import pl.aeh.rest_services_project_l1.domain.user.JwtResponse;
import pl.aeh.rest_services_project_l1.service.AppUserService;

import java.util.List;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @GetMapping("/check")
    public ResponseEntity<Void> check() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createAppUser(@RequestBody AppUser user) {

        AppUser userToSave = new AppUser();
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));

        this.appUserService.saveUser(userToSave);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<AppUser> getAllUsers() {
        AppUser user = appUserService.getUser("admin");

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authAppUser(@RequestBody AuthRequest request) {
        if (request == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AppUser user = this.appUserService.getUser(request.getUsername());

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = this.jwtService.generateToken(user.getUsername());
            return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
