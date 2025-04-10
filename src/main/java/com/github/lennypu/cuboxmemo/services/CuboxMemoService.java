package com.github.lennypu.cuboxmemo.services;

import com.github.lennypu.cuboxmemo.settings.CuboxMemoSettings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service
public final class CuboxMemoService {
    private static final Logger LOG = Logger.getInstance(CuboxMemoService.class);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Gson gson = new Gson();

    public void sendToApi(String content, Runnable onSuccess, Consumer<String> onError) {
        CuboxMemoSettings settings = ApplicationManager.getApplication().getService(CuboxMemoSettings.class);
        if (settings.getApiUrl().isBlank()) {
            onError.accept("API URL is not configured. Please configure it in Settings > Tools > Cubox Memo");
            return;
        }

        executorService.submit(() -> {
            try {
                URL url = new URL(settings.getApiUrl());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON request body
                JsonObject requestBody = new JsonObject();
                requestBody.addProperty("type", "memo");
                requestBody.addProperty("content", content);
                
                // Convert tags string to array
                String[] tagsArray = settings.getTags().split(",");
                JsonArray tagsJsonArray = new JsonArray();
                Arrays.stream(tagsArray)
                      .map(String::trim)
                      .filter(tag -> !tag.isEmpty())
                      .forEach(tagsJsonArray::add);
                requestBody.add("tags", tagsJsonArray);

                // Send the request
                try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8)) {
                    writer.write(requestBody.toString());
                    writer.flush();
                }

                // Get the response
                int responseCode = connection.getResponseCode();
                String response;
                if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder responseBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            responseBuilder.append(line);
                        }
                        response = responseBuilder.toString();
                    }
                } else {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                        StringBuilder responseBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            responseBuilder.append(line);
                        }
                        response = responseBuilder.toString();
                    }
                }

                // Handle response
                final String finalResponse = response;
                if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                    ApplicationManager.getApplication().invokeLater(onSuccess);
                } else {
                    ApplicationManager.getApplication().invokeLater(() -> onError.accept("API error: " + finalResponse));
                }

            } catch (Exception e) {
                LOG.error("Error sending to Cubox API", e);
                ApplicationManager.getApplication().invokeLater(() -> onError.accept("Error: " + e.getMessage()));
            }
        });
    }
}
