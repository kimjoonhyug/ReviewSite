/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcharm.review.model.Address;
import com.teamcharm.review.model.Image;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.repository.AddressRepository;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.PlaceRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author b005
 */
@Component
public class DatabaseFiller {

    private final List<File> zipFiles;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${database.info.domain}")
    private String infoDomain;

    @Value("${database.info.zip}")
    private String zip;

    //Use string format
    @Value("${database.info.menu}")
    private String menu;

    //USE string format
    @Value("${database.info.info}")
    private String info;
    
    @Value("${yogiyo.key}")
    private String key;
    
    @Value("${yogiyo.secret}")
    private String secret;
    
    @Value("${yogiyo.key.key}")
    private String keykey;
    
    @Value("${yogiyo.secret.key}")
    private String secretkey;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ImageRepository imageRepository;
    
    @Autowired
    AddressRepository addressRepository;

    public DatabaseFiller(List<File> zipFiles) {
        this.zipFiles = zipFiles;
        httpClient = new OkHttpClient();
        objectMapper = new ObjectMapper();
    }

    public void fill()  {
        zipFiles.forEach((zipFile) -> {
            try {
                fillWithOne(zipFile);
            } catch (Exception ex) {
                Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    private void fillWithOne(File file) throws Exception{
        try (Scanner sc = new Scanner(file)) {
            String token;
            while (sc.hasNext()) {
                token = sc.next();
                if (!token.contains("-")) {
                    Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, "Token: {0}", token);
                    Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, "File Name: {0}", file.getName());
                    throw new Exception("Not a proper zipfile");
                }
                token = token.replace("-", "");
                try {
                    parseResponse(responseFromZipCode(token));
                } catch(IOException e) {
                    Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, "Possibly Bad Zip", e);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String responseFromZipCode(String token) throws IOException {
        Request request = new Request.Builder()
                .url(infoDomain+zip+token)
                .addHeader(keykey, key)
                .addHeader(secretkey, secret)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                
                throw new IOException("Unexpected code " + response);
            }
            
            return response.body().string();
        }
    }

    private void parseResponse(String responseFromZipCode) throws IOException {
        JsonNode root = objectMapper.readTree(responseFromZipCode);
        Place place; 
        for(JsonNode node : root.get("restaurants")) {
            place = new Place();
            place.setPhone(new BigInteger(node.get("phone").textValue()));
            place.setType(Place.Type.get(node.get("categories").get(0).textValue()));
            Image image = new Image();
            image.setLocation(infoDomain+node.get("logo_url").textValue());
            image = imageRepository.save(image);
            place.setLogo(image);
            place.setName(node.get("name").textValue());
            place.setHours(node.get("open_time_description").textValue());
            place.setId(node.get("id").asLong());
            Address address = parseAddress(node.get("address").textValue());
            address = addressRepository.save(address);
            place.setAddress(address);
            place.setRating(node.get("review_avg").asDouble());
            place.setLat(node.get("lat").asDouble());
            place.setLng(node.get("lng").asDouble());
            placeRepository.save(place);
        }
    }

    private Address parseAddress(String str) {
        String[] piece = str.split(" ");
        Address address = new Address();
        address.setSido(piece[0]);
        address.setSigungu(piece[1]);
        if(piece[2].endsWith("가")  || piece[2].contains("로")) {
            address.setDoro(piece[2]);
            address.setDong(piece[2]);
        }
        else address.setDong(piece[2]);
        
        if(piece.length == 4) {
            address.setDetail(piece[3]);
        } else if (piece.length == 5){
            address.setDetail(piece[3]+piece[4]);
        }
        return address;
        
        
    }
    
    

}
