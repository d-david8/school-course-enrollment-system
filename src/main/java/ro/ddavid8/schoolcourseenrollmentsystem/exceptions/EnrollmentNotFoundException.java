package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class EnrollmentNotFoundException extends RuntimeException{

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}