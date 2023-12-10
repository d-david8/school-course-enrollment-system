package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class EnrollmentInvalidDataException extends RuntimeException{

    public EnrollmentInvalidDataException(String message) {
        super(message);
    }
}