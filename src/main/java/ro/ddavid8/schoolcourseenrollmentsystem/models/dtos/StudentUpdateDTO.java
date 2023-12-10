package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentUpdateDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Size(min = 3, message = "The first name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The first name must contain a maximum of 200 characters.")
    private String firstName;
    @Size(min = 3, message = "The last name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The last name must contain a maximum of 200 characters.")
    private String lastName;
    @Size(min = 3, message = "The email must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The email must contain a maximum of 200 characters.")
    private String email;
    private LocalDate birthDate;
}