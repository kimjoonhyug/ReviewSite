/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.repository;

import com.teamcharm.review.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author b005
 */
@Repository
public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {
    
    Page<Place> findByAddressZipCode(int zipCode, Pageable page);
    Page<Place> findAllByAddressDongContainingIgnoreCaseOrAddressSidoContainingIgnoreCaseOrAddressSigunguContainingIgnoreCaseOrNameContaining(String search, String search2, String search3, String search4, Pageable page);
    Page<Place> findAllByType(Place.Type type, Pageable page);
    
    Page<Place> findByAddressZipCodeOrAddressDong(int zipCode, String dong, Pageable page);
    
}
