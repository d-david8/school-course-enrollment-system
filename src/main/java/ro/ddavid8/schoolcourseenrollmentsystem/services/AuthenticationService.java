package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.AuthenticateRequest;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.JwtAuthenticationResponse;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.RegisterRequest;

public interface AuthenticationService {

    public JwtAuthenticationResponse register(RegisterRequest request);

    public JwtAuthenticationResponse authenticate(AuthenticateRequest request);
}
