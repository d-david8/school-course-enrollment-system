package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;

import java.util.List;

@Repository
public interface CustomCourseRepository {
    public List<Course> findCoursesByCriteria(String courseName, String description, Sort sort);
}