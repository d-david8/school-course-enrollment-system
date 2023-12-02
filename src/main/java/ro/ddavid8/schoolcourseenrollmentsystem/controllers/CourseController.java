package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.services.CourseService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses APIs", description = "Endpoints for managing the courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.createCourse(courseDTO));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCoursesFilteredAndSorted(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "courseName") String orderBy,
            @RequestParam(required = false, defaultValue = "asc") String orderDirection) {
        return ResponseEntity.ok(courseService.getAllCoursesFilteredAndSorted(courseName, description, orderBy, orderDirection));
    }
}