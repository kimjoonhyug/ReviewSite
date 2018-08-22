/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Address;
import com.teamcharm.review.model.Image;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.teamcharm.review.model.Member;
import com.teamcharm.review.model.Menu;
import com.teamcharm.review.model.MenuItem;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.Review;
import com.teamcharm.review.repository.AddressRepository;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.MemberRepository;
import com.teamcharm.review.repository.MenuItemRepository;
import com.teamcharm.review.repository.MenuRepository;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.repository.ReviewRepository;
import com.teamcharm.review.service.PlaceService;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b003
 */
@Controller
public class HomeController {

    @Autowired
    ReviewRepository reviewRepository;
    
    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Autowired
    MenuRepository menuRepository;
    
    @Autowired
    PlaceService placeService;

    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ImageRepository imageRepository;
    
    @Autowired 
    PasswordEncoder passwordEncoder;
    
    @Value("${place.image.save-location-path}")
    private String saveLocationPath;

    @GetMapping(value = {"/home", "/"})
    public String home(Member member,Model model) {
        model.addAttribute("places",placeRepository.findAll(PageRequest.of(0,4)).getContent());
        return "home";
    }

    @PostMapping("/new")
    public String newReview(Review review, @AuthenticationPrincipal Member member, long placeId) {
        Optional<Place> option = placeRepository.findById(placeId);
        if (option.isPresent()) {
            review.setReviewDate(LocalDateTime.now());
            review.setReviewer(member);
            review.setPlace(option.get());
            reviewRepository.save(review);
            return "redirect:/place/"+placeId;
        } else 
            return "home";
    }

    @GetMapping("/register")
    public String register() {
            
        return "join";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }

    @PostMapping("/register")
    public String register(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return "redirect:/login";
    }

    @GetMapping("/place/{id}")
    public String place(Model model, @PathVariable long id) {

        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            model.addAttribute("place", place.get());
            
            model.addAttribute("phone", Util.makePhone(place.get().getPhone().toString()));
            return "place";
        } else {
            return "home";
        }
    }
    
    @PostMapping("/place/{id}/menu")
    public String newMenuItem(Model model, @PathVariable long id, MenuItem menuItem, @RequestParam MultipartFile file, HttpServletRequest request) {
        Optional<Place> option = placeRepository.findById(id);
        if(!option.isPresent())
            return "home";
        Place place = option.get();
        Menu menu = place.getMenu();
        List<MenuItem> menuItems = menu.getItems();
        
        menuItem.setImage(saveImage(id,request.getContextPath(), file));
        menuItem.setMenu(menu);
        menuItemRepository.save(menuItem);
        
        menuItems.add(menuItem);
        menu.setItems(menuItems);
        menuRepository.save(menu);
        
        place.setMenu(menu);
        placeRepository.save(place);
        
        return "redirect:/place/"+ place.getId();
    }
    
    private Image saveImage(long id, String contextPath, MultipartFile file) {
        
        String path = makePath(id);
        
        File directory = (new File(path));
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            file.transferTo(new File(path + file.getOriginalFilename()));
            Image image = new Image(contextPath+ "/images/"+file.getOriginalFilename());
            image = imageRepository.save(image);
            return image;
        } catch (IOException | IllegalStateException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @PostMapping("/place")
    public String place(Place place, Address address) {
        address = addressRepository.save(address);
        place.setAddress(address);
        placeService.newPlace(place);
        return "redirect:/place/" + place.getId();
    }
    
    @PostMapping("/place/delete")
    public String place(long id) {
        placeService.deletePlace(id);
        return "redirect:/home";
    }
    
    

    @GetMapping("/kind/{type}")
    public String kind(@PathVariable String type, Pageable page, Model model) {
        Page<Place> result = placeRepository.findAllByType(Place.Type.valueOf(type), page);
        model.addAttribute("pages", result.getTotalPages());
        model.addAttribute("places", result.getContent());
        return "kind";
    }

    private String makePath(long placeId) {
        StringBuilder sb = new StringBuilder(saveLocationPath);
        sb.append("\\");
        sb.append(placeId);
        sb.append("\\images\\");
        return sb.toString();
    }

}
