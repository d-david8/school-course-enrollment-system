package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(String courseName, String description, Sort sort);

    List<Course> findByCourseNameContainingIgnoreCase(String courseName, Sort sort);

    List<Course> findByDescriptionContainingIgnoreCase(String description, Sort sort);
}