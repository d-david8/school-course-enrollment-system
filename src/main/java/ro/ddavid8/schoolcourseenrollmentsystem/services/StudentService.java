package ro.ddavid8.schoolcourseenrollmentsystem.services;

import org.springframework.data.util.Pair;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentUpdateDTO;

import java.util.List;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO studentDTO);

    StudentUpdateDTO updateStudent(Long studentId, StudentUpdateDTO studentUpdateDTO);

    public List<StudentDTO> getAllStudents();

    public void deleteUserById(Long studentId);
}