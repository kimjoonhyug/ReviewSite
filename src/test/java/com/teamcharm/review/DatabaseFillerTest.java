/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcharm.review.repository.MenuItemRepository;
import com.teamcharm.review.repository.MenuRepository;
import com.teamcharm.review.repository.PlaceRepository;
import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author b005
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase()
public class DatabaseFillerTest {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    DatabaseFiller filler;

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuItemRepository menuItemRepository;
    private ObjectMapper objectMapper;

    public DatabaseFillerTest() {
    }

    /**
     * Test of fill method, of class DatabaseFiller.
     */
    @Test
    public void testFill() throws Exception {
        objectMapper = new ObjectMapper();

        filler.fill();
        System.out.println(placeRepository.count());
        System.out.println(menuRepository.count());
        System.out.println(menuItemRepository.count());
        Assertions.assertThat(placeRepository.count() > 50).isTrue();

    }

}
