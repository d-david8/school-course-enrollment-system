package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.EnrollmentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.services.EnrollmentService;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollment APIs", description = "Endpoints for managing the enrolments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enroll(@RequestBody EnrollmentDTO enrollmentDTO) {
        return ResponseEntity.ok(enrollmentService.enroll(enrollmentDTO));
    }
}