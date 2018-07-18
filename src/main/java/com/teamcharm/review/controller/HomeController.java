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
/**
 *
 * @author b003
 */
@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String home(Member member){
        
        return "home";
    }
    
    @PostMapping("/new")
    public String newReview(){
        
        return "new";
    }
    
    @PostMapping("/register")
    public String register(){
        
        return "register";
    }
    
    @PostMapping("/store")
    public String store(Place place){
        
        return "store";
    }
    
    @PostMapping("/login")
    public String login(Member member){
        
        return "login";
    }
}
