/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author b005
 */
@Configuration
public class TestConfig {

    @Bean
    public List<File> getFiles() {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("daeguzip.txt"));
        files.add(new File("seoulzip.txt"));
        files.add(new File("busanzip.txt"));
        files.add(new File("daejeonzip.txt"));
        return files;
    }

}
