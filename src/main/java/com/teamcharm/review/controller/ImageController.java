/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Image;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.PlaceImage;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.PlaceRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b005
 */
@RestController
@RequestMapping("/images")
public class ImageController {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @Autowired
    ImageRepository imageRepository;
    
    @Value("${place.image.save-location-path}")
    private String saveLocationPath;
    
    @PostMapping("place/{id}")
    public ResponseEntity newImage(@PathVariable long id, 
            @RequestParam List<MultipartFile> files ){
        
        if(files== null)
            return ResponseEntity.noContent().build();
        
        for (MultipartFile file : files) {
            if(!file.getContentType().contains("image"))
                return ResponseEntity.badRequest().body("Not an Image");
        }
        
        Optional<Place> placeOptional = placeRepository.findById(id);
        if(!placeOptional.isPresent())
            return ResponseEntity.notFound().build();
        Place place = placeOptional.get();
        
        if(place.getImages() ==null)
            place.setImages(new ArrayList<>());
        
        List<PlaceImage> images = place.getImages();
                
        String path = makePath(id, Image.ImageType.PLACE);
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
        
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("{type}/{fileName}")
    public ResponseEntity<byte[]> findFile(@PathVariable String fileName,
            @PathVariable() Image.ImageType type,
            @PathVariable long placeId) throws FileNotFoundException {
        String path = makePath(placeId,type) + fileName;
        try {
            return ResponseEntity.ok(Files.readAllBytes(Paths.get(path)));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.notFound().build();
        }
    }
    
     private String makePath(long placeId, Image.ImageType type) {
        StringBuilder sb = new StringBuilder(saveLocationPath);
        sb.append("\\");
        sb.append(type);
        sb.append("\\");
        sb.append(placeId);
        sb.append("\\images\\");
        return sb.toString();
    }
    
}
