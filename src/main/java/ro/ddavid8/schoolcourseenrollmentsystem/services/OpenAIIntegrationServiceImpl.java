package ro.ddavid8.schoolcourseenrollmentsystem.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ro.ddavid8.schoolcourseenrollmentsystem.exceptions.OpenAIException;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.OpenAIRequestDTO;
import ro.ddavid8.schoolcourseenrollmentsystem.models.dtos.OpenAIResponseDTO;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAIIntegrationServiceImpl implements OpenAIIntegrationService {

    private final Environment environment;
    private final ObjectMapper objectMapper;

    @Override
    public String getOpenAIResponse(String prompt) {
        String jsonBody = null;
        try {
            jsonBody = objectMapper.writeValueAsString(new OpenAIRequestDTO(environment.getProperty("open.ai.model"), prompt));
        } catch (JsonProcessingException e) {
            log.error("Error on mapping the OpenAI request body");
            throw new OpenAIException("An error occur while try to obtain the recommended products");
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url(Objects.requireNonNull(environment.getProperty("open.ai.url")))
                .header("Authorization", "Bearer " + environment.getProperty("open.ai.key"))
                .post(requestBody)
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                log.info("Open AI response with success");
                return objectMapper.readValue(responseBody, OpenAIResponseDTO.class).getChoices().get(0).getMessage().getContent();
            }
            log.error("Request was not successfully");
            throw new OpenAIException("An error occur while try to obtain the recommended products");
        } catch (IOException ex) {
            throw new OpenAIException("An error occur while try to obtain the recommended products");
        }
    }
}