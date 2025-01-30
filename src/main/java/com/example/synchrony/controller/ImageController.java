package com.example.synchrony.controller;



import com.example.synchrony.model.User;
import com.example.synchrony.service.ImageService;
import com.example.synchrony.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    public ImageController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    // Endpoint for uploading images
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam String username, @RequestParam String password,
                                              @RequestParam("file") MultipartFile file) {
        // Authenticate user (check username and password)
        User user = userService.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Call Imgur API to upload the image
        try {
            String imgurId = imageService.uploadImage(file); // Call the ImageService method to upload to Imgur
            String imageUrl = "https://imgur.com/" + imgurId; // Use the ID returned from Imgur API

            // Associate image URL with the user (optional)
            user.getImages().add(imageUrl);
            userService.save(user); // Save the user to update their profile

            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully: " + imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }

    // Endpoint for deleting images
    @DeleteMapping("/delete/{imgurId}")
    public ResponseEntity<String> deleteImage(@PathVariable String imgurId) {
        try {
            imageService.deleteImageByImgurId(imgurId);
            return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image: " + e.getMessage());
        }
    }
}


