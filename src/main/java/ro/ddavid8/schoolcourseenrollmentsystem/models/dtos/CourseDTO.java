package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Size(min = 3, message = "{validation.course_name.size.too_short}")
    @Size(max = 200, message = "{validation.course_name.size.too_long}")
    private String courseName;
    @Size(min = 3, message = "{validation.course_description.size.too_short}")
    @Size(max = 200, message = "{validation.course_description.size.too_long}")
    private String description;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}