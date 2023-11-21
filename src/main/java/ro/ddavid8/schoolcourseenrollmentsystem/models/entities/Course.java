package ro.ddavid8.schoolcourseenrollmentsystem.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}
