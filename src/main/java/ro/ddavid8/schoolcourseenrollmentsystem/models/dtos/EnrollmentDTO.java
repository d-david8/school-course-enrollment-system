package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

import java.time.LocalDate;

@Data
public class EnrollmentDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private LocalDate enrolmentDate;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int progress;
    private Long studentId;
    private Long courseId;
}