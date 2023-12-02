package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.InvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.StudentRepository;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final ObjectMapper objectMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        try {
            studentDTO.setCreatedAt(LocalDateTime.now());
            Student studentSaved = studentRepository.save(objectMapper.convertValue(studentDTO, Student.class));
            return objectMapper.convertValue(studentSaved, StudentDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Invalid email address");
        }
    }
}