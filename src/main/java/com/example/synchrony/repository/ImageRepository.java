package com.example.synchrony.repository;
import com.example.synchrony.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ImageRepository extends JpaRepository<Image, Long>{
    void deleteByImgurId(String imgurId);
}




