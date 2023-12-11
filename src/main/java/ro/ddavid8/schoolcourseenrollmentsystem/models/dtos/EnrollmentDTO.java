package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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