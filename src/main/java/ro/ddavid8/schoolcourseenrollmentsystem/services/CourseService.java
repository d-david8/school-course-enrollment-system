package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;

public interface CourseService {
    public CourseDTO createCourse(CourseDTO courseDTO);
}