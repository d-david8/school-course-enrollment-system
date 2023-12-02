package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Enrollment;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentAndCourse(Student student,  Course course);
}