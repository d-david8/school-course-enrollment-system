package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String message) {
        super(message);
    }
}