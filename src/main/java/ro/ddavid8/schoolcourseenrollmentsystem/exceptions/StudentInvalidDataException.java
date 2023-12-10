package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class StudentInvalidDataException extends RuntimeException {

    public StudentInvalidDataException(String message) {
        super(message);
    }
}