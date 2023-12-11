package ro.ddavid8.schoolcourseenrollmentsystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

@RequiredArgsConstructor
@Service
public class TemplateBuilderServiceImpl implements TemplateBuilderService {

    private final TemplateEngine templateEngine;

    @Override
    public String createStudentBodyEmail(Student student) {
        Context context = new Context();
        String url = "http://localhost:8080/api/students/" + student.getId();
        context.setVariable("name", student.getFirstName());
        context.setVariable("url", url);
        return templateEngine.process("create-student", context);
    }

    @Override
    public String createEnrollmentBodyEmail(Student student, Course course) {
        Context context = new Context();
        String url = "http://localhost:8080/api/courses/" + course.getId();
        context.setVariable("name", student.getFirstName());
        context.setVariable("courseName", course.getCourseName());
        context.setVariable("url", url);
        return templateEngine.process("enrollment", context);
    }
}