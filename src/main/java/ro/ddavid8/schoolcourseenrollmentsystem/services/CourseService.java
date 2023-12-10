package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;

import java.util.List;

public interface CourseService {
    public CourseDTO createCourse(CourseDTO courseDTO);

    public List<CourseDTO> getAllCoursesFilteredAndSorted(String courseName, String description, String orderBy, String orderDirection);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    void deleteCourse(Long id);
}