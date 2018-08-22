/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Place;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.service.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author b005
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @Autowired
    PlaceServiceImpl placeService;
    
    @ResponseBody
    @GetMapping("/map")
    public ResponseEntity<Page<Place>> searchByDong(Pageable pageable, int zipCode, String dong) {
        return ResponseEntity.ok(placeRepository.findByAddressZipCodeOrAddressDong(zipCode, dong, pageable));
    }
    
    
    @GetMapping()
    public String searchGeneral(Pageable pageable, String search, Model model) {
        Page<Place> page = placeService.generalSearch(search,pageable);
        model.addAttribute("places", page.getContent() );
        model.addAttribute("pages", page.getTotalPages());
        return "search";
    }
    @GetMapping("/type")
    public String pageByType(Pageable pageable, String type, Model model) {
        Page<Place> page =  placeRepository.findAllByType(Place.Type.valueOf(type), pageable);
        model.addAttribute("places", page.getContent() );
        model.addAttribute("pages", page.getTotalPages());
        return "search";
    }
    
}
