package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotNull(message = "The first name is mandatory")
    @Size(min = 3, message = "The first name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The first name must contain a maximum of 200 characters.")
    private String firstName;
    @NotNull(message = "The last name is mandatory")
    @Size(min = 3, message = "The last name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The last name must contain a maximum of 200 characters.")
    private String lastName;
    @NotNull(message = "The first email is mandatory")
    @Size(min = 3, message = "The email must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The email must contain a maximum of 200 characters.")
    private String email;
    private LocalDate birthDate;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}