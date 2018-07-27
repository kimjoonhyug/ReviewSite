/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review;

import com.teamcharm.review.repository.PlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author b005
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
public class MapSearchTest {
    
    @Autowired
    PlaceRepository placeRepository;
    
    @Test
    public void mapSearch() {
        System.out.println(placeRepository.count());
        System.out.println(placeRepository.findByAddressZipCode( 700290, null).getTotalElements());
    }
    
    
}
