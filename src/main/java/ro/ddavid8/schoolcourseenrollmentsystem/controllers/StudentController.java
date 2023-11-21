package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.StudentDTO;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents(
            @RequestParam Long studentId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam LocalDate birthDate) {
        return null;
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        return null;
    }

    @DeleteMapping
    public String deleteStudent(@PathVariable Long studentId) {
        return null;
    }
}
