package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.services.StudentService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/students")
@Tag(name = "Students APIs", description = "Endpoints for managing the students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity(studentService.createStudent(studentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, studentDTO));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteUserById(studentId);
    }
}