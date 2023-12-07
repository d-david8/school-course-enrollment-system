package ro.ddavid8.schoolcourseenrollmentsystem.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.User;

public interface UserService {
    UserDetailsService userDetailsService();
    public User save(User newUser);
}
