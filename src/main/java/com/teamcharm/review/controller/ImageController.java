/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Image;
import com.teamcharm.review.model.Place;
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
            @RequestParam MultipartFile file) {

        if (file == null) {
            return ResponseEntity.noContent().build();
        }

        Optional<Place> placeOptional = placeRepository.findById(id);
        if (!placeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Place place = placeOptional.get();
        
        place.setLogo(saveImage(id,file));
        placeRepository.save(place);

        return ResponseEntity.accepted().build();
    }

    private Image saveImage(long id, MultipartFile file) {
        
        String path = makePath(id);
        
        File directory = (new File(path));
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            file.transferTo(new File(path + file.getOriginalFilename()));
            Image image = new Image(file.getOriginalFilename());
            image = imageRepository.save(image);
            return image;
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> findFile(@PathVariable String fileName,
            @PathVariable() Image.ImageType type,
            @PathVariable long placeId) throws FileNotFoundException {
        String path = makePath(placeId) + fileName;
        try {
            return ResponseEntity.ok(Files.readAllBytes(Paths.get(path)));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.notFound().build();
        }
    }

    private String makePath(long placeId) {
        StringBuilder sb = new StringBuilder(saveLocationPath);
        sb.append("\\");
        sb.append(placeId);
        sb.append("\\images\\");
        return sb.toString();
    }

}
