package ro.ddavid8.schoolcourseenrollmentsystem.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 3, message = "{validation.course_name.size.too_short}")
    @Size(max = 200, message = "{validation.course_name.size.too_long}")
    @Column(name = "course_name", nullable = false, unique = true)
    private String courseName;
    @Size(min = 3, message = "{validation.course_description.size.too_short}")
    @Size(max = 200, message = "{validation.course_description.size.too_long}")
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}