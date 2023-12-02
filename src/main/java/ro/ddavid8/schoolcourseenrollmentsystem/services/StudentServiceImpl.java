package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.InvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.StudentNotFoundException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;

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
            throw new InvalidDataException("Student already exist!");
        }
    }

    @Override
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Invalid student id!"));
        try {
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setEmail(studentDTO.getEmail());
            student.setBirthDate(studentDTO.getBirthDate());

            Student savedStudent = studentRepository.save(student);
            return objectMapper.convertValue(savedStudent, StudentDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Invalid email address");
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(student -> objectMapper.convertValue(student, StudentDTO.class)).toList();
    }

    @Override
    public void deleteUserById(Long studentId) {
        if (studentRepository.existsById(studentId))
            studentRepository.deleteById(studentId);
        else
            throw new StudentNotFoundException("Invalid student id!");
    }
}