package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
