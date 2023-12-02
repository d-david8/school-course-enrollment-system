package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}