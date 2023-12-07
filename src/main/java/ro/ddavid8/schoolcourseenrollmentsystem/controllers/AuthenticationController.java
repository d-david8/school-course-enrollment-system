package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.JwtAuthenticationResponse;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.SignInRequest;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.SignUpRequest;
import ro.ddavid8.schoolcourseenrollmentsystem.services.AuthenticationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/authentication")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}
