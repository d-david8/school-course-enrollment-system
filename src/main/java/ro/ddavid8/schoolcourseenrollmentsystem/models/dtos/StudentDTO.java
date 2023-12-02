package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Size(min = 3, message = "{validation.firstName.size.too_short}")
    @Size(max = 200, message = "{validation.firstName.size.too_long}")
    private String firstName;
    @Size(min = 3, message = "{validation.firstName.size.too_short}")
    @Size(max = 200, message = "{validation.firstName.size.too_long}")
    private String lastName;
    @Size(min = 3, message = "{validation.email.size.too_short}")
    @Size(max = 200, message = "{validation.email.size.too_long}")
    private String email;
    private LocalDate birthDate;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}