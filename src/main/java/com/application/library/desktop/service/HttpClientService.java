package com.application.library.desktop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpClientService {


    @Value("${server.url}")
    private String serverUrl;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public <T> T doPost(String path, Object body, Class<T> responseClass) {
        try {
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + path))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(asJsonString(body)))
                    .build();

            HttpResponse<String> response = client.send(build, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), responseClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T doGet(String path, Class<T> responseClass) {
        try {
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + path))
                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(build, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return objectMapper.readValue(response.body(), responseClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String asJsonString(final Object obj) throws JsonProcessingException {
        if (obj == null) return null;

        return objectMapper.writeValueAsString(obj);
    }
}
