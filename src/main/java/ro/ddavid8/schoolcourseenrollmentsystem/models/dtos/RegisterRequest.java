package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String firstName;
    String lastName;
    String username;
    String password;
}