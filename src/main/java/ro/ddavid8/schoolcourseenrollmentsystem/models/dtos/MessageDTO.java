package ro.ddavid8.schoolcourseenrollmentsystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private String role;
    private String content;
}