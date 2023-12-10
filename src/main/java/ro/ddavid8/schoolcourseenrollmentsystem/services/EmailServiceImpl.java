package ro.ddavid8.schoolcourseenrollmentsystem.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.EmailDTO;

@Slf4j
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailDTO emailDTO) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(emailDTO.getTo());
            helper.setFrom(emailDTO.getFrom());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getBody(), true);

            javaMailSender.send(mimeMessage);
            log.info("Email to {}, was sent.", emailDTO.getTo());
        } catch (MessagingException e) {
            log.info("Email to {}, could not be sent.", emailDTO.getTo());
        }
    }
}