package com.example.Portfolio.services;

import com.example.Portfolio.dtos.supabase.auth.SupabaseAuthRequestDTO;
import com.example.Portfolio.dtos.supabase.auth.SupabaseAuthResponse;
import com.example.Portfolio.utils.ErrorMessageUtils;
import com.example.Portfolio.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SupabaseService {
    @Value("${supabase.configurations.authUrl}")
    private final String authUrl;

    @Value("${supabase.configurations.apiKey}")
    private final String apiKey;

    @Value("${supabase.configurations.username}")
    private final String username;

    @Value("${supabase.configurations.password}")
    private final String password;

    @Value("${supabase.configurations.apiUrl}")
    private final String apiUrl;

    @Value("${supabase.configurations.bucket}")
    private final String bucket;

    public SupabaseService(String authUrl, String apiKey, String username, String password, String apiUrl, String bucket) {
        this.authUrl = authUrl;
        this.apiKey = apiKey;
        this.username = username;
        this.password = password;
        this.apiUrl = apiUrl;
        this.bucket = bucket;
    }

    public String getSupabaseToken() {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            SupabaseAuthRequestDTO requestBody = new SupabaseAuthRequestDTO();
            requestBody.setEmail(this.username);
            requestBody.setPassword(this.password);
            HttpRequest httpRequest = HttpRequest.newBuilder().
                    uri(URI.create(this.authUrl))
                    .header("apiKey", this.apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(MapperUtils.writeValueAsString(requestBody)))
                    .build();

           HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
           SupabaseAuthResponse response =  MapperUtils.writeValueAsResponse(httpResponse.body(), SupabaseAuthResponse.class);
           return response.getAccessToken();
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageUtils.SERVER_ERROR);
        }
    }
}
