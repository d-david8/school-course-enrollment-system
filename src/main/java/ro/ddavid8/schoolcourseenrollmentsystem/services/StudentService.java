package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentUpdateDTO;

import java.util.List;

public interface StudentService {

    public StudentDTO createStudent(StudentDTO studentDTO);

    StudentUpdateDTO updateStudent(Long studentId, StudentUpdateDTO studentUpdateDTO);

    public List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    public void deleteStudentById(Long studentId);
}