package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.CourseInvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.CourseNotFoundException;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.StudentNotFoundException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.CourseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
            log.info("Course with id {} was successfully saved", savedCourse.getId());
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
        log.info("Data successfully fetched.");
        return courseResult.stream().map(course -> objectMapper.convertValue(course, CourseDTO.class)).toList();
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Invalid course id."));
        course.setCourseName(courseDTO.getCourseName());
        if (courseDTO.getDescription() != null) {
            course.setDescription(courseDTO.getDescription());
        }
        try {
            Course savedCourse = courseRepository.save(course);
            log.info("The course with id {} was successfully updates", savedCourse.getId());
            return objectMapper.convertValue(savedCourse, CourseDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new CourseInvalidDataException("The course name already exists.");
        }
    }
    @Override
    public void deleteCourse(Long id){
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            log.info("Course with id {} was deleted  with success!", id);
        }
        else {
            throw new StudentNotFoundException("Invalid course id!");
        }
    }
}