package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.JwtAuthenticationResponse;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.SignInRequest;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.SignUpRequest;

public interface AuthenticationService {

    public JwtAuthenticationResponse signup(SignUpRequest request);
    public JwtAuthenticationResponse signin(SignInRequest request);
}
