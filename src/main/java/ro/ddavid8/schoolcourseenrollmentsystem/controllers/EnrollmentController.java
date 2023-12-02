package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.EnrollmentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.services.EnrollmentService;

@Validated
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enroll(@RequestBody EnrollmentDTO enrollmentDTO) {
        return ResponseEntity.ok(enrollmentService.enroll(enrollmentDTO));
    }
}