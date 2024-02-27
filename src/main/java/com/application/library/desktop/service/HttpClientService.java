package com.application.library.desktop.service;

import com.application.library.desktop.constants.RequestPathConstants;
import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.supplier.ClientSupplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static com.application.library.desktop.supplier.Supplier.executeRequest;


@Service
public class HttpClientService {


    @Value("${server.url}")
    private String serverUrl;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // SERVICE
    private final ClientSupplier clientSupplier;

    public HttpClientService(ClientSupplier clientSupplier) {
        this.clientSupplier = clientSupplier;
    }


    public <T> T doPost(String path, Object body, Class<T> responseClass) {

        return clientSupplier.executeClientSend(() -> {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + path))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(asJsonString(body)));

            if (!RequestPathConstants.PUBLIC_PATHS.contains(path)) {
                builder.header("Authorization", getToken());
            }

            HttpResponse<String> response = executeRequest(client.send(builder.build(), HttpResponse.BodyHandlers.ofString()));
            return objectMapper.readValue(response.body(), responseClass);
        });
    }

    public <T> T doPost(Map<String, String> params, String path, Class<T> responseClass) {
        return clientSupplier.executeClientSend(() -> {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(getFullPathByParams(path, params)))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getToken())
                    .POST(HttpRequest.BodyPublishers.noBody());

            HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();

            HttpResponse<String> response = executeRequest(client.send(builder.build(), responseBodyHandler));
            return objectMapper.readValue(response.body(), responseClass);
        });
    }


    public <T> T doGet(String path, Class<T> responseClass) {
        try {
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + path))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getToken())
                    .GET()
                    .build();

            HttpResponse<String> response = executeRequest(client.send(build, HttpResponse.BodyHandlers.ofString()));
            return objectMapper.readValue(response.body(), responseClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T doGet(String path, Map<String, String> params, Class<T> responseClass) {
        return clientSupplier.executeClientSend(() -> {
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(getFullPathByParams(path, params)))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getToken())
                    .GET()
                    .build();

            HttpResponse<String> response = executeRequest(client.send(build, HttpResponse.BodyHandlers.ofString()));
            return objectMapper.readValue(response.body(), responseClass);
        });
    }

    public <T> T doPut(String path, Object body, Class<T> responseClass) {
        return clientSupplier.executeClientSend(() -> {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + path))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getToken())
                    .PUT(HttpRequest.BodyPublishers.ofString(asJsonString(body)));

            HttpResponse<String> response = executeRequest(client.send(builder.build(), HttpResponse.BodyHandlers.ofString()));
            return objectMapper.readValue(response.body(), responseClass);
        });
    }

    public <T> T doPut(String path, Class<T> responseClass, Map<String, String> params) {
        return clientSupplier.executeClientSend(() -> {
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(getFullPathByParams(path, params)))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getToken())
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = executeRequest(client.send(build, HttpResponse.BodyHandlers.ofString()));
            return objectMapper.readValue(response.body(), responseClass);
        });
    }

    public <T> T doDelete(String path, Class<T> responseClass) {
        try {
            HttpRequest build = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl + path))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getToken())
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(build, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), responseClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFullPathByParams(String path, Map<String, String> params) {
        StringBuilder fullPath = new StringBuilder(serverUrl + path);
        if (params != null && !params.isEmpty()) {
            fullPath.append("?");
            params.forEach((key, value) -> fullPath.append(key).append("=").append(value).append("&"));
        }

        return fullPath.toString();
    }


    private String asJsonString(final Object obj) throws JsonProcessingException {
        if (obj == null) return null;

        return objectMapper.writeValueAsString(obj);
    }
    
    private String getToken() {
        return "Bearer " + SystemVariables.AUTHORIZATION_TOKEN;
    }
}
