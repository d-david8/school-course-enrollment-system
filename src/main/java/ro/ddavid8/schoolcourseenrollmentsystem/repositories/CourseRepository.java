package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}