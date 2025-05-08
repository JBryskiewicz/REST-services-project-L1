package pl.aeh.rest_services_project_l1.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.aeh.rest_services_project_l1.config.JwtService;
import pl.aeh.rest_services_project_l1.domain.user.AppUser;
import pl.aeh.rest_services_project_l1.domain.user.AuthRequest;
import pl.aeh.rest_services_project_l1.domain.user.JwtResponse;
import pl.aeh.rest_services_project_l1.service.AppUserService;

@Controller()
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
        userToSave.setEmail(user.getEmail());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));

        this.appUserService.saveUser(userToSave);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authAppUser(@RequestBody AuthRequest request) {
        if (request == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        AppUser user = this.appUserService.getUser(request.getEmail());

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = this.jwtService.generateToken(user.getUsername());
            return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
