package com.example.synchrony.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImgurConfig {

    @Value("${imgur.client-id}")
    private String clientId;

    @Value("${imgur.api-url}")
    private String apiUrl;

    public String getClientId() {
        return clientId;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
