package ro.ddavid8.schoolcourseenrollmentsystem.services;

import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO emailDTO);
}