/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.teamcharm.review.model.Member;
import com.teamcharm.review.model.Menu;
import com.teamcharm.review.model.MenuItem;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.Review;
import com.teamcharm.review.repository.AddressRepository;
import com.teamcharm.review.repository.MemberRepository;
import com.teamcharm.review.repository.MenuItemRepository;
import com.teamcharm.review.repository.MenuRepository;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.repository.ReviewRepository;
import com.teamcharm.review.service.ImageService;
import com.teamcharm.review.service.MemberService;
import com.teamcharm.review.service.PlaceService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    PlaceService placeService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private MemberService memberService;

    @GetMapping(value = {"/home", "/"})
    public String home(Member member,Model model) {
        model.addAttribute("places",placeRepository.findAll(PageRequest.of(0,4)).getContent());
        return "home";
    }

    @PostMapping("/new")
    public String newReview(Review review, @AuthenticationPrincipal Member member, long placeId) {
        if(!placeService.newReview(placeId, member, review))
            return "home";
        else return "redirect:/place/"+placeId;
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
        memberService.saveMember(member);
        return "redirect:/home";
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
        if(placeService.newMenuItem(id, menuItem, file, request))
             return "redirect:/place/"+ id;
        else return "home";
    }
    
    @PostMapping("/place")
    public String place(Place place, Address address, MultipartFile file, HttpServletRequest request) {
        address = addressRepository.save(address);
        place.setAddress(address);
        place = placeService.newPlace(place);
        
        if(file != null)
            place.setLogo(imageService.saveImage(place.getId(),request.getContextPath(), file));
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

}
