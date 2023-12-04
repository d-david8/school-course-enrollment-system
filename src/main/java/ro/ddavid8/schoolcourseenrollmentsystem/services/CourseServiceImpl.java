package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.CourseInvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.CourseRepository;

import java.time.LocalDateTime;
import java.util.List;

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
            throw new CourseInvalidDataException("Course already exist!");
        }
    }

    @Override
    public List<CourseDTO> getAllCoursesFilteredAndSorted(String courseName, String description, String orderBy, String orderDirection) {
        List<Course> courseResult;
        Sort sort = Sort.by(orderDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);

        if (courseName != null && description != null) {
            courseResult = courseRepository.findByCourseNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(courseName, description, sort);
        } else if (courseName != null) {
            courseResult = courseRepository.findByCourseNameContainingIgnoreCase(courseName, sort);
        } else if (description != null) {
            courseResult = courseRepository.findByDescriptionContainingIgnoreCase(description, sort);
        } else {
            courseResult = courseRepository.findAll(sort);
        }
        return courseResult.stream().map(course -> objectMapper.convertValue(course, CourseDTO.class)).toList();
    }
}