package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;

import java.time.LocalDate;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO studentDTO);

}
