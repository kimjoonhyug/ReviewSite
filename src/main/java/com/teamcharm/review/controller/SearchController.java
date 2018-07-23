/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Place;
import com.teamcharm.review.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author b005
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @GetMapping("/map")
    public String searchByDong(Pageable pageable, String dong, Model model) {
        Page<Place> page = placeRepository.findByAddressDong(pageable, dong);
        model.addAttribute("places", page.getContent());
        model.addAttribute("pages", page.getTotalPages());
        return "map";
    }
    
}
