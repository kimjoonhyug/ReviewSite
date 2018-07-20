/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.teamcharm.review.model.Member;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.Review;
import com.teamcharm.review.repository.MemberRepository;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.repository.ReviewRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author b003
 */
@Controller
public class HomeController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PlaceRepository placeRepository;
    
    
    @Value("${place.image.save-location-path}")
    private String saveLocationPath;
    
    @GetMapping(value = {"/home","/"})
    public String home(Member member){
        
        return "home";
    }
    
    @PostMapping("/new")
    public String newReview(Review review){
        reviewRepository.save(review);
        return "new";
    }
    
    @GetMapping("/register")
    public String register(){
        
        return "join";
    }
    
    @PostMapping("/register")
    public String register(Member member){
        memberRepository.save(member);
        return "redirect:/login";
    }
    
    @GetMapping("/place/{id}")
    public String place(Model model, @PathVariable long id){
        
        Optional<Place> place = placeRepository.findById(id);
        if(place.isPresent()){
            model.addAttribute("place",place);
            return "place";
        }else{
            return "home";
        }
    }
    
    @PostMapping("/place")
    public String place(Place place){
        placeRepository.save(place);
        return "place";
    }
    
    @GetMapping("{id}/images/{fileName}")
    public ResponseEntity<byte[]> findFile(@PathVariable String fileName,
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
