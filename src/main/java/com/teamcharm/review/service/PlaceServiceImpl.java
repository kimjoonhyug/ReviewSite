/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.service;

import com.teamcharm.review.controller.HomeController;
import com.teamcharm.review.model.Image;
import com.teamcharm.review.model.Member;
import com.teamcharm.review.model.Menu;
import com.teamcharm.review.model.MenuItem;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.Review;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.MenuItemRepository;
import com.teamcharm.review.repository.MenuRepository;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.repository.ReviewRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b005
 */
@Service
public class PlaceServiceImpl implements PlaceService {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @Autowired
    ImageRepository imageRepository;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Autowired
    MenuRepository menuRepository;
    
    @Autowired
    ReviewRepository reviewRepository;
    
    @Override
    public ResponseEntity addImage(long id, List<MultipartFile> files, String path) {
        Optional<Place> placeOptional = placeRepository.findById(id);
        if(!placeOptional.isPresent())
            return ResponseEntity.notFound().build();
        Place place = placeOptional.get();
        
        if(place.getImages() ==null)
            place.setImages(new ArrayList<>());
        
        List<Image> images = place.getImages();
                
        File directory = (new File(path));
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        files.forEach((file) -> {
            try {
                file.transferTo(new File(path + file.getOriginalFilename()));
                Image image = new Image(file.getOriginalFilename());
                images.add(imageRepository.save(image));
            } catch (IOException | IllegalStateException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        place.setImages(images);
        placeRepository.save(place);
        
        return ResponseEntity.accepted().build();
    }
    
    @Override
    public Page<Place> generalSearch(String search, Pageable page) {
        return placeRepository.findAllByAddressDongContainingIgnoreCaseOrAddressSidoContainingIgnoreCaseOrAddressSigunguContainingIgnoreCaseOrNameContaining( search,search,search,search, page);
    }
    
    @Override
    public void newPlace(Place place) {
        placeRepository.save(place);
    }
    
    @Override
    public void deletePlace(long id) {
        placeRepository.deleteById(id);
    }    

    @Override
    public boolean newMenuItem(long id, MenuItem menuItem, MultipartFile file, HttpServletRequest request) {
        Optional<Place> option = placeRepository.findById(id);
        if(!option.isPresent())
            return false;
        Place place = option.get();
        Menu menu = place.getMenu();
        List<MenuItem> menuItems = menu.getItems();
        
        menuItem.setImage(imageService.saveImage(id,request.getContextPath(), file));
        menuItem.setMenu(menu);
        menuItemRepository.save(menuItem);
        
        menuItems.add(menuItem);
        menu.setItems(menuItems);
        menuRepository.save(menu);
        
        place.setMenu(menu);
        placeRepository.save(place);
        
        return true;
        
    }
    
    @Override
    public boolean newReview(long id, Member member, Review review) {
        Optional<Place> option = placeRepository.findById(id);
        if (!option.isPresent())
            return false;
        
        review.setReviewDate(LocalDateTime.now());
        review.setReviewer(member);
        review.setPlace(option.get());
        reviewRepository.save(review);
        
        return true;
    }
        
    
}
