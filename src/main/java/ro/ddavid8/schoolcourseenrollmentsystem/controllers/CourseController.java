package ro.ddavid8.schoolcourseenrollmentsystem.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.CourseDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.services.CourseService;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO){
        return ResponseEntity.ok(courseService.createCourse(courseDTO));
    }
}
