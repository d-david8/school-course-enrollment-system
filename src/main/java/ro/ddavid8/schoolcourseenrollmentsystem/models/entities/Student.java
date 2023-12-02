package ro.ddavid8.schoolcourseenrollmentsystem.models.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students", indexes = {@Index(name = "idx_email", columnList = "email")})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Size(min = 3, message = "{validation.first_name.size.too_short}")
    @Size(max = 200, message = "{validation.first_name.size.too_long}")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Size(min = 3, message = "{validation.last_name.size.too_short}")
    @Size(max = 200, message = "{validation.last_name.size.too_long}")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Size(min = 3, message = "{validation.email.size.too_short}")
    @Size(max = 200, message = "{validation.email.size.too_long}")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name= "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();
}