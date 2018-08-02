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
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.MemberRepository;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.repository.ReviewRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    ImageRepository imageRepository;

    @Value("${place.image.save-location-path}")
    private String saveLocationPath;

    @GetMapping(value = {"/home", "/"})
    public String home(Member member) {

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
            return "new";
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
        memberRepository.save(member);
        return "redirect:/login";
    }

    @GetMapping("/place/{id}")
    public String place(Model model, @PathVariable long id) {

        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            model.addAttribute("place", place.get());
            return "place";
        } else {
            return "home";
        }
    }

    @PostMapping("/place")
    public String place(Place place) {
        //TODO save place
        return "home";
    }

    @GetMapping("/kind/{type}")
    public String kind(Place place) {
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
