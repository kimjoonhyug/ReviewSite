/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.config;

import com.teamcharm.review.DatabaseFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author b005
 */
@Component
public class FillDataBase implements CommandLineRunner{
    
    @Autowired
    DatabaseFiller databaseFiller;
    
    @Override
    public void run(String... args) throws Exception {
        databaseFiller.run();
        
    }
    
}
