/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.service;

import com.teamcharm.review.model.Member;
import com.teamcharm.review.model.MenuItem;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.model.Review;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b005
 */
public interface PlaceService {

    ResponseEntity addImage(long id, List<MultipartFile> files, String path);
    
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void deletePlace(long id);

    Page<Place> generalSearch(String search, Pageable page);
    
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void newPlace(Place place);
    
    boolean newMenuItem(long id, MenuItem item, MultipartFile file, HttpServletRequest request);
    
    boolean newReview(long id, Member member, Review review);
    
}
