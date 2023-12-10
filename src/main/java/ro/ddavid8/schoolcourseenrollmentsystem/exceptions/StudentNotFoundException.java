package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}