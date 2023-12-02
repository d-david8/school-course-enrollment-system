package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.InvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.CourseRepository;

import java.time.LocalDateTime;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ObjectMapper objectMapper) {
        this.courseRepository = courseRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        try {
            courseDTO.setCreatedAt(LocalDateTime.now());
            Course savedCourse = courseRepository.save(objectMapper.convertValue(courseDTO, Course.class));
            return objectMapper.convertValue(savedCourse, CourseDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Course already exist!");
        }
    }
}