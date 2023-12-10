package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotNull(message = "Course name is mandatory.")
    @Size(min = 3, message = "The course name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The course name must contain a maximum of 200 characters.")
    private String courseName;
    @Size(min = 3, message = "The course description must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The course description must contain a maximum of 200 characters.")
    private String description;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}