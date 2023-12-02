package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO studentDTO);

    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);
}