/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.service;

import com.teamcharm.review.controller.HomeController;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.PlaceImage;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.PlaceRepository;
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
public class PlaceService {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @Autowired
    ImageRepository imageRepository;
    
    public ResponseEntity addImage(long id, List<MultipartFile> files, String path) {
        Optional<Place> placeOptional = placeRepository.findById(id);
        if(!placeOptional.isPresent())
            return ResponseEntity.notFound().build();
        Place place = placeOptional.get();
        
        if(place.getImages() ==null)
            place.setImages(new ArrayList<>());
        
        List<PlaceImage> images = place.getImages();
                
        File directory = (new File(path));
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        files.forEach((file) -> {
            try {
                file.transferTo(new File(path + file.getOriginalFilename()));
                PlaceImage image = new PlaceImage(file.getOriginalFilename());
                image.setPlace(place);
                images.add(imageRepository.save(image));
            } catch (IOException | IllegalStateException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        place.setImages(images);
        placeRepository.save(place);
        
        return ResponseEntity.accepted().build();
    }
    
}
