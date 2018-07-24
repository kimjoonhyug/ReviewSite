/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import com.teamcharm.review.model.Place;
import com.teamcharm.review.repository.PlaceRepository;
import com.teamcharm.review.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author b005
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @Autowired
    PlaceService placeService;
    
    @GetMapping("/map")
    public ResponseEntity<Page<Place>> searchByDong(Pageable pageable, String dong) {
        return ResponseEntity.ok(placeRepository.findAllByAddressDong( dong, pageable));
    }
    
    @GetMapping()
    public ResponseEntity<Page<Place>> searchGeneral(Pageable pageable, String search) {
        return ResponseEntity.ok(placeService.generalSearch(search,pageable));
    }
    
}
