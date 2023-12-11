package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class CourseInvalidDataException extends RuntimeException {

    public CourseInvalidDataException(String message) {
        super(message);
    }
}