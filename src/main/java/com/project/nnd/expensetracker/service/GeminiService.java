package com.project.nnd.expensetracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    public String generateExpenseReport(List<Map<String, Object>> expenseData, int wordLimit) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert expense data to JSON string
            String jsonData = objectMapper.writeValueAsString(expenseData);

            // ✅ Improved Prompt
            String prompt = "Generate a simple and concise expense summarry my spending." +
                            " Mention where I spent how much, suggest areas where I can save money," +
                            " and include a fun fact about daily budgeting. The report should be in plain text format." +
                            " Ensure all amounts are in Indian Rupees (₹). Here are my expenses: " + jsonData;

            // Prepare request payload
            Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                    Map.of("parts", List.of(Map.of("text", prompt)))
                )
            );

            // Convert request body to JSON
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(requestBodyJson, headers);

            // Send API request
            String apiUrl = GEMINI_API_URL + "?key=" + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

            // Parse response JSON to extract the generated text
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            String report = jsonResponse
                    .path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text")
                    .asText("Failed to generate report.");

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing report.";
        }
    }
}
