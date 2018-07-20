/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.service;

import com.teamcharm.review.controller.HomeController;
import com.teamcharm.review.model.PlaceImage;
import com.teamcharm.review.model.Review;
import com.teamcharm.review.model.ReviewImage;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.ReviewRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b005
 */
@Service
public class ReviewService {
    
    @Autowired
    ReviewRepository reviewRepository;
     
    @Autowired
    ImageRepository imageRepository;
    
//    public ResponseEntity addImage(long id, List<MultipartFile> files, String path) {
//        Optional<Review> optional = reviewRepository.findById(id);
//        if(!optional.isPresent())
//            return ResponseEntity.notFound().build();
//        Review review = optional.get();
//        
//        if(review.getImages() ==null)
//            review.setImages(new ArrayList<>());
//        
//        List<ReviewImage> images = review.getImages();
//                
//        File directory = (new File(path));
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//        
//        files.forEach((file) -> {
//            try {
//                file.transferTo(new File(path + file.getOriginalFilename()));
//                ReviewImage image = new ReviewImage(file.getOriginalFilename());
//                image.setReview(review);
//                images.add(imageRepository.save(image));
//            } catch (IOException | IllegalStateException ex) {
//                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//        review.setImages(images);
//        reviewRepository.save(review);
//        
//        return ResponseEntity.accepted().build();
//    }
    
}
