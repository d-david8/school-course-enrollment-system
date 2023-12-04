package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String message) {
        super(message);
    }
}