package ro.ddavid8.schoolcourseenrollmentsystem.services;

import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.InvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.EnrollmentDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Enrollment;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.CourseRepository;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.EnrollmentRepository;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public EnrollmentDTO enroll(EnrollmentDTO enrollmentDTO) {

        Optional<Student> optionalStudent = studentRepository.findById(enrollmentDTO.getStudentId());
        if (optionalStudent.isEmpty()) {
            throw new InvalidDataException("Invalid student id!");
        }
        Student student = optionalStudent.get();
        Optional<Course> optionalCourse = courseRepository.findById(enrollmentDTO.getCourseId());
        if (optionalCourse.isEmpty()) {
            throw new InvalidDataException("Invalid course id!");
        }
        Course course = optionalCourse.get();
        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new InvalidDataException("Student already enrolled at this course!");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(optionalStudent.get());
        enrollment.setCourse(optionalCourse.get());
        enrollment.setEnrolmentDate(LocalDate.now());
        enrollment.setProgress(0);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        EnrollmentDTO enrollmentDTOResponse = new EnrollmentDTO();
        enrollmentDTOResponse.setId(savedEnrollment.getId());
        enrollmentDTOResponse.setStudentId(savedEnrollment.getStudent().getId());
        enrollmentDTOResponse.setCourseId(savedEnrollment.getCourse().getId());
        enrollmentDTOResponse.setProgress(savedEnrollment.getProgress());
        enrollmentDTOResponse.setEnrolmentDate(savedEnrollment.getEnrolmentDate());

        return enrollmentDTOResponse;
    }
}