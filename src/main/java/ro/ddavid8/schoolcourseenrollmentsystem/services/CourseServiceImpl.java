package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.CustomCourseRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CustomCourseRepository customCourseRepository;
    private final OpenAIIntegrationService openAIIntegrationService;
    private final ObjectMapper objectMapper;

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
    public List<CourseDTO> findCoursesByCriteria(String courseName, String description, String orderBy, String orderDirection) {
        Sort sort = Sort.by(orderDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);
        List<Course> courseResult = customCourseRepository.findCoursesByCriteria(courseName, description, sort);
        log.info("Data successfully fetched for courses.");
        return courseResult.stream().map(course -> objectMapper.convertValue(course, CourseDTO.class)).toList();
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Invalid course id."));
        log.info("Data successfully fetched for course with id {}.", course.getId());
        return objectMapper.convertValue(course, CourseDTO.class);
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
    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            log.info("Course with id {} was deleted  with success!", id);
        } else {
            throw new StudentNotFoundException("Invalid course id!");
        }
    }

    @Override
    public List<String> getRecommended() {
        List<Course> courses = courseRepository.findAll();
        Map<String, Integer> coursesMap = courses.stream()
                .collect(Collectors.toMap(Course::getCourseName, course -> course.getStudents().size()));
        String openAiPrompt = "Based on the existing courses and the number of enrolled students for " +
                "each course, we recommend adding two new courses to the available options. The list of " +
                "courses is as follows:" + coursesMap +
                ". Please provide two examples separated by a comma and and nothing else in response";
        return Arrays.stream(openAIIntegrationService.getOpenAIResponse(openAiPrompt).split(", ")).toList();
    }
}