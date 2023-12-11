package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.domain.Sort;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;

import java.util.List;

public interface CustomCourseRepository {

    public List<Course> findCoursesByCriteria(String courseName, String description, Sort sort);
}