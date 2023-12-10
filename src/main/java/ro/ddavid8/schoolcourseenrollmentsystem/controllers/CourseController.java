package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(courseService.createCourse(courseDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findCoursesByCriteria(@RequestParam(required = false) String courseName, @RequestParam(required = false) String description, @RequestParam(required = false, defaultValue = "id") String orderBy, @RequestParam(required = false, defaultValue = "asc") String orderDirection) {
        return ResponseEntity.ok(courseService.findCoursesByCriteria(courseName, description, orderBy, orderDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteCourseBy(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}