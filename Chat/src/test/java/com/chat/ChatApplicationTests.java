package com.chat;

import com.chat.DTO.ChatRequest;
import com.chat.DTO.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChatApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String apiURL;

    @Test
    void testChat() {
        String prompt = "Hello, how are you?";
        String model = "gpt-3.5-turbo";

        ChatRequest request = new ChatRequest(model, prompt);
        ResponseEntity<ChatResponse> responseEntity = restTemplate.postForEntity(apiURL, request, ChatResponse.class);

        assertEquals(201, responseEntity.getStatusCodeValue());

        ChatResponse chatResponse = responseEntity.getBody();
        assertNotNull(chatResponse);

        assertNotNull(chatResponse.getChoices());
        assertFalse(chatResponse.getChoices().isEmpty());
        assertNotNull(chatResponse.getChoices().get(0).getMessage());
    }
}
