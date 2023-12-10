package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

public interface TemplateBuilderService {

    String createStudentBodyEmail(Student student);

    String createEnrollmentBodyEmail(Student student, Course course);
}