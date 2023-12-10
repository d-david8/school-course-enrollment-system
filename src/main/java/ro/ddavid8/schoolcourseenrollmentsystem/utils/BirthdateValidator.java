package ro.ddavid8.schoolcourseenrollmentsystem.utils;

import lombok.experimental.UtilityClass;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.StudentInvalidDataException;

import java.time.LocalDate;

@UtilityClass
public class BirthdateValidator {

    public static void validateBirthDateRange(LocalDate birthdate) {
        if (!(birthdate.isBefore(LocalDate.now().minusYears(18)) && birthdate.isAfter(LocalDate.now().minusYears(100)))) {
            throw new StudentInvalidDataException("Birthdate is not in the allowed range!");
        }
    }
}