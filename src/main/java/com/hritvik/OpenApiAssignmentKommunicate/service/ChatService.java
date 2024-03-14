package com.hritvik.OpenApiAssignmentKommunicate.service;

import com.hritvik.OpenApiAssignmentKommunicate.dto.ApiResponseDto;
import com.hritvik.OpenApiAssignmentKommunicate.dto.ChatRequestDto;
import com.hritvik.OpenApiAssignmentKommunicate.dto.ExternalApiRequestDto;
import com.hritvik.OpenApiAssignmentKommunicate.dto.OpenAiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ChatService {

    @Value("${openai.apiKey}")
    private String apiKey;

    @Value("${openai.apiUrl}")
    private String apiUrl;

    @Autowired
    RestTemplate restTemplate;

    /**
     * A method to get a response using the given ChatRequestDto.
     *
     * @param request the ChatRequestDto containing the request details
     * @return the ResponseEntity containing the ApiResponseDto
     */
    public ResponseEntity<ApiResponseDto> getResponse(ChatRequestDto request) {

        try {
            log.info("Got a Request: {}", request);

            // Validate request
            if (request == null ||request.getPartial_text() == null || request.getPartial_text().isEmpty()|| StringUtils.isAnyBlank(request.getPartial_text())) {
                log.error("Error: Partial text is null or empty");
                return ResponseEntity.ok(new ApiResponseDto("Error: Partial text is null or empty"));
            }

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);
            log.debug("Headers: {}", headers);

            // Prepare the message list for the external API request
            List<ExternalApiRequestDto.Message> messageList = List.of(new ExternalApiRequestDto.Message("user", request.getPartial_text()));
            log.debug("Message list: {}", messageList);

            // Create the external API request payload
            ExternalApiRequestDto externalApiRequestDto = new ExternalApiRequestDto();
            externalApiRequestDto.setModel("gpt-3.5-turbo");
            externalApiRequestDto.setMessages(messageList);
            log.debug("External API request: {}", externalApiRequestDto);

            // Create the request entity with the payload and headers
            HttpEntity<Object> requestEntity = new HttpEntity<>(externalApiRequestDto, headers);
            log.debug("Request entity: {}", requestEntity);


            // Make a POST request to the external API
            ResponseEntity<OpenAiResponseDto> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, OpenAiResponseDto.class);
            log.debug("Response entity: {}", responseEntity);

            // Check if the response status is OK
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                // Return the response with the content from the API
                log.info("Response: {}", responseEntity.getBody());
                return ResponseEntity.ok(new ApiResponseDto(Objects.requireNonNull(responseEntity.getBody()).getChoices().get(0).getMessage().getContent()));
            }

            // Return a response with an error message if the response status is not OK
            log.error("Error: {}", responseEntity.getStatusCode());
            return ResponseEntity.ok(new ApiResponseDto("Error: " + responseEntity.getStatusCode()));

        } catch (Exception e) {
            // Return a response with an error message if an exception occurs
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.ok(new ApiResponseDto("Error: " + e.getMessage()));
        }
    }
}
