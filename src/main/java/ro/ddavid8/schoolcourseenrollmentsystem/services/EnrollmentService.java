package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.EnrollmentDTO;

public interface EnrollmentService {

    public EnrollmentDTO enroll(EnrollmentDTO enrollmentDTO);
}