package com.teamcharm.review;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ReviewApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ReviewApplication.class)
                .properties("spring.config.name:application,config")
                .build().run(args);
    }

    @Bean
    public List<File> getFiles() {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("daeguzip.txt"));
//        files.add(new File("seoulzip.txt"));
//        files.add(new File("busanzip.txt"));
//        files.add(new File("daejeonzip.txt"));
        return files;
    }
}
