package com.chat.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chat.DTO.ChatRequest;
import com.chat.DTO.ChatResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class chatController {
	@Value("${openai.model}")
	private String model;

	@Autowired
	private RestTemplate template;

	@Value("${openai.api.url}")
	private String apiURL;

	@GetMapping("/chat")
	public ResponseEntity<ChatResponse> chat(@RequestParam("prompt") String prompt) {
		log.info("Prompt: {}", prompt);
		ChatRequest request = new ChatRequest("gpt-3.5-turbo", prompt + " generate a random joke");

		log.info("Request: {}", request);
		ChatResponse chatResponse = template.postForObject(apiURL, request, ChatResponse.class);
		return new ResponseEntity<>(chatResponse, HttpStatus.CREATED);
	}
}
