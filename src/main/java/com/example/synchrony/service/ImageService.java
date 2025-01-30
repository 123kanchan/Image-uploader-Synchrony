package com.example.synchrony.service;


import com.example.synchrony.model.Image;
import com.example.synchrony.model.ImgurResponse;
import com.example.synchrony.model.User;
import com.example.synchrony.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

//

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageService {

    @Value("${imgur.client-id}")  // Read client-id from application.yaml
    private String clientId;

    public String uploadImage(MultipartFile file) {
        try {
            String imgurApiUrl = "https://api.imgur.com/3/image";  // Imgur API endpoint

            // Create a RestTemplate instance to make HTTP requests
            RestTemplate restTemplate = new RestTemplate();

            // Set headers with the authorization key (Imgur API client-id)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Client-ID " + clientId);

            // Convert the MultipartFile to a byte array
            byte[] imageBytes = file.getBytes();

            // Prepare the request entity with file and headers
            HttpEntity<byte[]> entity = new HttpEntity<>(imageBytes, headers);

            // Make the request to the Imgur API and capture the response
            ResponseEntity<String> response = restTemplate.exchange(
                    imgurApiUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // Use Jackson ObjectMapper to parse the response body into a POJO
            ObjectMapper objectMapper = new ObjectMapper();
            ImgurResponse imgurResponse = objectMapper.readValue(response.getBody(), ImgurResponse.class);

            // Extract the imgurId and imageUrl
            String imgurId = imgurResponse.getData().getId();
            String imageUrl = imgurResponse.getData().getLink();

            // Return the image URL
            return imageUrl;  // You can also return imgurId if needed

        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Return null in case of an error
        }
    }
    // Delete image from Imgur
    public void deleteImageByImgurId(String imgurId) {
        try {
            String imgurApiUrl = "https://api.imgur.com/3/image/" + imgurId;  // Imgur delete API endpoint

            // Create a RestTemplate instance to make HTTP requests
            RestTemplate restTemplate = new RestTemplate();

            // Set headers with the authorization key (Imgur API client-id)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Client-ID " + clientId);

            // Prepare the request entity with headers (No body required for DELETE)
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the DELETE request to the Imgur API
            restTemplate.exchange(
                    imgurApiUrl,
                    HttpMethod.DELETE,
                    entity,
                    String.class
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
