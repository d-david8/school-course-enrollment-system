package ro.ddavid8.schoolcourseenrollmentsystem.util;

import lombok.experimental.UtilityClass;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.StudentInvalidDataException;

import java.util.regex.Pattern;

@UtilityClass
public class EmailValidator {
    public static void validateEmail(String email){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean isValid= Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
        if(!isValid){
            throw new StudentInvalidDataException("The email address is not in the correct format!");
        }
    }
}
