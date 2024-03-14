package com.hritvik.OpenApiAssignmentKommunicate.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiResponseDto {
    private List<Choice> choices;
    private long created;
    private String id;
    private String model;
    private String object;
    private Usage usage;


    @Setter
    @Getter
    static
    public class Choice {
        private String finish_reason;
        private int index;
        private Message message;
        private Object logprobs;

    }

    @Setter
    @Getter
    static
    public class Message {
        private String content;
        private String role;

    }

    @Setter
    @Getter

    public static class Usage {
        private int completion_tokens;
        private int prompt_tokens;
        private int total_tokens;

    }
}

