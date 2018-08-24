/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.service;

import com.teamcharm.review.controller.HomeController;
import com.teamcharm.review.model.Image;
import com.teamcharm.review.repository.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b005
 */
@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Value("${place.image.save-location-path}")
    private String saveLocationPath;
    
    public Image saveImage(long id, String contextPath, MultipartFile file) {
        
        String path = makePath(id);
        
        File directory = (new File(path));
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            file.transferTo(new File(path + file.getOriginalFilename()));
            Image image = new Image(contextPath + "/images/" + file.getOriginalFilename());
            image = imageRepository.save(image);
            return image;
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String makePath(long placeId) {
        StringBuilder sb = new StringBuilder(saveLocationPath);
        sb.append("\\");
        sb.append(placeId);
        sb.append("\\images\\");
        return sb.toString();
    }
    
    
    
}
