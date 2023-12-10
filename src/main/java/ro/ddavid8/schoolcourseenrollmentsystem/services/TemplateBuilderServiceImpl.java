package ro.ddavid8.schoolcourseenrollmentsystem.services;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Student;

@Service
public class TemplateBuilderServiceImpl implements TemplateBuilderService {

    private final TemplateEngine templateEngine;

    public TemplateBuilderServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String createStudentBodyEmail(Student student) {
        Context context = new Context();
        String url = "http://localhost:8080/api/students/" + student.getId();
        context.setVariable("name",
                student.getFirstName());
        context.setVariable("url", url);
        return templateEngine.process("create-student", context);
    }
}