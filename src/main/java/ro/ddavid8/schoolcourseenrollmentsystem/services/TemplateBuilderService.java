package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

public interface TemplateBuilderService {
    String createStudentBodyEmail(Student student);
}
