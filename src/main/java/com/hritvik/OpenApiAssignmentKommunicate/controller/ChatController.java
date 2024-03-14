package com.hritvik.OpenApiAssignmentKommunicate.controller;


import com.hritvik.OpenApiAssignmentKommunicate.dto.ApiResponseDto;
import com.hritvik.OpenApiAssignmentKommunicate.dto.ChatRequestDto;
import com.hritvik.OpenApiAssignmentKommunicate.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {


    @Autowired
    ChatService chatService;

    /**
     * Handles the chat request and returns the response.
     *
     * @param request the chat request data transfer object
     * @return the response data transfer object
     */
    @PostMapping("/complete_chat")
    public ResponseEntity<ApiResponseDto> chat(@RequestBody ChatRequestDto request) {
        // Calling service to get response
        return chatService.getResponse(request);

    }
}
