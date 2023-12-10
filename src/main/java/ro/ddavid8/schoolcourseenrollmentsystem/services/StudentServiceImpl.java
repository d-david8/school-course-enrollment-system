package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.StudentInvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.StudentNotFoundException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentUpdateDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.StudentRepository;
import ro.ddavid8.schoolcourseenrollmentsystem.utils.BirthdateValidator;
import ro.ddavid8.schoolcourseenrollmentsystem.utils.EmailValidator;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
        EmailValidator.validateEmail(studentDTO.getEmail());
        if(studentDTO.getBirthDate()!=null) {
            BirthdateValidator.validateBirthDateRange(studentDTO.getBirthDate());
        }
        try {
            studentDTO.setCreatedAt(LocalDateTime.now());
            Student studentSaved = studentRepository.save(objectMapper.convertValue(studentDTO, Student.class));
            log.info("Student with id {} was saved with success!",studentSaved.getId());
            return objectMapper.convertValue(studentSaved, StudentDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new StudentInvalidDataException("Email address already exist!");
        }
    }

    @Override
    public StudentUpdateDTO updateStudent(Long studentId, StudentUpdateDTO studentUpdateDTO) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Invalid student id!"));
        try {
            if (studentUpdateDTO.getEmail() != null) {
                EmailValidator.validateEmail(studentUpdateDTO.getEmail());
                student.setEmail(studentUpdateDTO.getEmail());
            }
            if (studentUpdateDTO.getBirthDate() != null) {
                BirthdateValidator.validateBirthDateRange(studentUpdateDTO.getBirthDate());
                student.setBirthDate(studentUpdateDTO.getBirthDate());
            }
            student.setFirstName(studentUpdateDTO.getFirstName() != null ? studentUpdateDTO.getFirstName() : student.getFirstName());
            student.setLastName(studentUpdateDTO.getLastName() != null ? studentUpdateDTO.getLastName() : student.getLastName());
            Student savedStudent = studentRepository.save(student);
            log.info("Student with id {} was updated successfully", savedStudent.getId());
            return objectMapper.convertValue(savedStudent, StudentUpdateDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new StudentInvalidDataException("Email address already exists!");
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(student -> objectMapper.convertValue(student, StudentDTO.class)).toList();
    }

    @Override
    public void deleteStudentById(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            log.info("Student with id {} was deleted  with success!", studentId);
        }
        else {
            throw new StudentNotFoundException("Invalid student id!");
        }
    }
}