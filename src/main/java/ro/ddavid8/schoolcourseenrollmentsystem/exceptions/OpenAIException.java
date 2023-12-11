package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

public class OpenAIException extends RuntimeException {
    public OpenAIException(String message) {
        super(message);
    }
}