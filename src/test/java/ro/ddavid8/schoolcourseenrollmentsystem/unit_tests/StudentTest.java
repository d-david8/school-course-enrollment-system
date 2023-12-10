package ro.ddavid8.schoolcourseenrollmentsystem.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.StudentRepository;
import ro.ddavid8.schoolcourseenrollmentsystem.services.StudentServiceImpl;
import ro.ddavid8.schoolcourseenrollmentsystem.util.EmailValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentTest {


    private StudentRepository studentRepository;
    private ObjectMapper objectMapper;
    private StudentServiceImpl studentService;
    private LocalDateTime createdAt;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        objectMapper = mock(ObjectMapper.class);
        studentService = new StudentServiceImpl(studentRepository, objectMapper);
        createdAt = LocalDateTime.now();
    }

    @Test
    void createStudent_ShouldPass() {
        // Given
        StudentDTO inputStudentDTO = new StudentDTO();
        inputStudentDTO.setFirstName("John");
        inputStudentDTO.setLastName("Doe");

        Student inputStudent = new Student();
        inputStudent.setFirstName("John");
        inputStudent.setLastName("Doe");

        Student savedStudent = new Student();
        savedStudent.setFirstName("John");
        savedStudent.setLastName("Doe");
        savedStudent.setId(1L);
        savedStudent.setCreatedAt(createdAt);

        // When
        when(objectMapper.convertValue(inputStudentDTO, Student.class)).thenReturn(inputStudent);
        when(studentRepository.save(inputStudent)).thenReturn(savedStudent);
        when(objectMapper.convertValue(savedStudent, StudentDTO.class)).thenReturn(inputStudentDTO);

        // Then
        StudentDTO resultStudentDTO = studentService.createStudent(inputStudentDTO);
        resultStudentDTO.setId(1L);
        resultStudentDTO.setCreatedAt(createdAt);

        // Assert
        assertNotNull(resultStudentDTO);
        assertEquals(savedStudent.getId(), resultStudentDTO.getId());
        assertEquals(savedStudent.getFirstName(), resultStudentDTO.getFirstName());
        assertEquals(savedStudent.getLastName(), resultStudentDTO.getLastName());
        assertEquals(savedStudent.getCreatedAt(), resultStudentDTO.getCreatedAt());
    }
}