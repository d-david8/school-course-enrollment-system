package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;

import java.time.LocalDate;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO studentDTO);

    public StudentDTO getStudents(Long studentId, String firstName, String lastName, LocalDate birthDate);

    public StudentDTO updateStudent(StudentDTO studentDTO);

    public String deleteStudentById(StudentDTO studentDTO);
}
