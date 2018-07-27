/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcharm.review.model.Menu;
import com.teamcharm.review.repository.MenuItemRepository;
import com.teamcharm.review.repository.MenuRepository;
import com.teamcharm.review.repository.PlaceRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseFillerTest {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    DatabaseFiller filler;

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuItemRepository menuItemRepository;

    public DatabaseFillerTest() {
    }

    /**
     * Test of fill method, of class DatabaseFiller.
     */
    @Test
    public void testFill() throws Exception {

        filler.fill();
        System.out.println(placeRepository.count());
        System.out.println(menuRepository.count());
        System.out.println(menuItemRepository.count());
        System.out.println(placeRepository.findByAddressZipCode(704130, null).getTotalElements());
        Assertions.assertThat(placeRepository.count() > 50).isTrue();
        

    }

}
