package ro.ddavid8.schoolcourseenrollmentsystem.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "The first name is mandatory")
    @Size(min = 3, message = "The first name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The first name must contain a maximum of 200 characters.")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotNull(message = "The last name is mandatory")
    @Size(min = 3, message = "The last name must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The last name must contain a maximum of 200 characters.")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotNull(message = "The first email is mandatory")
    @Size(min = 3, message = "The email must contain a minimum of 3 characters.")
    @Size(max = 200, message = "The email must contain a maximum of 200 characters.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();
}