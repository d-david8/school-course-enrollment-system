package ro.ddavid8.schoolcourseenrollmentsystem.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.CourseInvalidDataException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.repositories.CourseRepository;
import ro.ddavid8.schoolcourseenrollmentsystem.services.CourseServiceImpl;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void createCourseSuccess_ShouldPass() {
        // Given
        CourseDTO validCourseDTO = CourseDTO.builder().courseName("Math").description("desc").build();
        LocalDateTime currentTime = LocalDateTime.of(2023, 1, 1, 10, 1);
        Course responseCourse = new Course(1L, "Math", "desc", currentTime, null);

        //When
        when(objectMapper.convertValue(validCourseDTO, Course.class)).thenReturn(responseCourse);
        when(courseRepository.save(responseCourse)).thenReturn(responseCourse);
        when(objectMapper.convertValue(responseCourse, CourseDTO.class)).thenReturn(validCourseDTO);

        // Then
        CourseDTO courseResponseDTO = courseService.createCourse(validCourseDTO);

        assertNotNull(courseResponseDTO);
        assertEquals(courseResponseDTO.getCourseName(),
                responseCourse.getCourseName());
    }

    @Test
    void createCourseDuplicateEntry_ShouldFail() {
        // Given
        CourseDTO inputCourseDTO = CourseDTO.builder().courseName("Name").description("Desc").build();
        Course responseCourse = new Course(1L, "Name", "Desc", LocalDateTime.now(), null);

        // When
        when(objectMapper.convertValue(inputCourseDTO, Course.class)).thenReturn(responseCourse);
        when(courseRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        // Then
        assertThrows(CourseInvalidDataException.class, () -> {
            courseService.createCourse(inputCourseDTO);
        });

        verify(courseRepository, times(1)).save(any());
        verify(objectMapper, times(1)).convertValue(inputCourseDTO, Course.class);
    }
}