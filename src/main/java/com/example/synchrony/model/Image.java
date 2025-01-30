package com.example.synchrony.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String imgurId; // Unique ID from Imgur API

    @Column(nullable = false)
    private String imageUrl; // Direct image link from Imgur

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Link image to a user

    // Default Constructor (Required by JPA)
    public Image() {
    }

    // Constructor for creating an image object
    public Image(String imgurId, String imageUrl, User user) {
        this.imgurId = imgurId;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgurId() {
        return imgurId;
    }

    public void setImgurId(String imgurId) {
        this.imgurId = imgurId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

