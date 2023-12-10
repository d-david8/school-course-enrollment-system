package ro.ddavid8.schoolcourseenrollmentsystem.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    public ExceptionHandlerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // student exceptions
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> studentNotFoundException(StudentNotFoundException studentNotFoundException) {
        log.error(studentNotFoundException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", studentNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(StudentInvalidDataException.class)
    public ResponseEntity<String> studentInvalidDataException(StudentInvalidDataException studentInvalidDataException) {
        log.error(studentInvalidDataException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", studentInvalidDataException.getMessage())), BAD_REQUEST);
    }

    // course exceptions
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> courseNotFoundException(CourseNotFoundException courseNotFoundException) {
        log.error(courseNotFoundException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", courseNotFoundException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(CourseInvalidDataException.class)
    public ResponseEntity<String> courseInvalidDataException(CourseInvalidDataException courseInvalidDataException) {
        log.error(courseInvalidDataException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", courseInvalidDataException.getMessage())), BAD_REQUEST);
    }

    // enrollment exceptions
    @ExceptionHandler(EnrollmentInvalidDataException.class)
    public ResponseEntity<String> enrollmentNotFoundException(EnrollmentInvalidDataException enrollmentInvalidDataException) {
        log.error(enrollmentInvalidDataException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", enrollmentInvalidDataException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<String> enrollmentNotFoundException(EnrollmentNotFoundException enrollmentNotFoundException) {
        log.error(enrollmentNotFoundException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", enrollmentNotFoundException.getMessage())), BAD_REQUEST);
    }

    // validation exceptions
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> invalidEmailException(InvalidEmailException invalidEmailException) {
        log.error(invalidEmailException.getMessage());
        return new ResponseEntity<>(objectToString(Map.of("message", invalidEmailException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            String message = violation.getMessage();
            errors.put(fieldName, message);
            log.error(message);
        }
        return new ResponseEntity<>(objectToString(errors), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String defaultMessage = Objects.requireNonNull(error.getDefaultMessage());
            errors.put(error.getField(), defaultMessage);
        });
        return new ResponseEntity<>(objectToString(errors), HttpStatus.BAD_REQUEST);
    }

    private String objectToString(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException exception) {
            log.error("Error processing response to string");
            return "Internal error";
        }
    }
}