package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;

import java.util.List;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO studentDTO);

    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);

    public List<StudentDTO> getAllStudents();
}