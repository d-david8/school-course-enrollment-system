package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.AuthenticateRequest;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.JwtAuthenticationResponse;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.RegisterRequest;
import ro.ddavid8.schoolcourseenrollmentsystem.services.AuthenticationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/security")
@Tag(name = "Security APIs", description = "Endpoints for managing the authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authentication")
    public JwtAuthenticationResponse authenticate(@RequestBody AuthenticateRequest request) {
        return authenticationService.authenticate(request);
    }
}