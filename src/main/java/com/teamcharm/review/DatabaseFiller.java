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
import com.teamcharm.review.model.Menu;
import com.teamcharm.review.model.MenuItem;
import com.teamcharm.review.model.Place;
import com.teamcharm.review.repository.AddressRepository;
import com.teamcharm.review.repository.ImageRepository;
import com.teamcharm.review.repository.MenuItemRepository;
import com.teamcharm.review.repository.MenuRepository;
import com.teamcharm.review.repository.PlaceRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
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

    @Value("${database.store.minimum-amount}")
    private String amount;

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

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public DatabaseFiller(List<File> zipFiles) {
        this.zipFiles = zipFiles;
        httpClient = new OkHttpClient();
        objectMapper = new ObjectMapper();
    }

    public void run() {
        if (amount != null) {
            if (placeRepository.count() < Integer.parseInt(amount)) {
                fill();
            }
        }
    }

    public void fill() {
        zipFiles.forEach((zipFile) -> {
            try {
                fillWithOne(zipFile);
            } catch (Exception ex) {
                Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void fillWithOne(File file) throws Exception {
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
                    parseResponse(responseFromZipCode(token), Integer.parseInt(token));
                } catch (IOException e) {
                    Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, "Possibly Bad Zip", e);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String responseFromZipCode(String token) throws IOException {
        return generalYogiyoRequest(infoDomain + zip + token);
    }

    private String responseMenu(long placeId) throws IOException {
        return generalYogiyoRequest(infoDomain + String.format(menu, placeId));
    }

    private String generalYogiyoRequest(String path) throws IOException {
        Request request = new Request.Builder()
                .url(path)
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

    @Transactional
    private void parseResponse(String responseFromZipCode, int zipCode) throws IOException {
        JsonNode root = objectMapper.readTree(responseFromZipCode);
        for (JsonNode node : root.get("restaurants")) {
            insertPlace(node, zipCode);
        }
    }

    @Transactional
    public void insertPlace(JsonNode node, int zipCode) throws IOException {
        if (placeRepository.existsById(node.get("id").asLong())) {
            return;
        }
        Place place = new Place();
        place.setPhone(new BigInteger(node.get("phone").textValue()));
        place.setType(Place.Type.get(node.get("categories").get(0).textValue()));
        Image image = new Image();
        image.setLocation(infoDomain + node.get("logo_url").textValue());
        image = imageRepository.save(image);
        place.setLogo(image);
        place.setName(node.get("name").textValue());
        place.setHours(node.get("open_time_description").textValue());
        place.setId(node.get("id").asLong());
        Address address = parseAddress(node.get("address").textValue());
        address.setZipCode(zipCode);
        address = addressRepository.save(address);
        place.setAddress(address);
        place.setRating(node.get("review_avg").asDouble());
        place.setLat(node.get("lat").asDouble());
        place.setLng(node.get("lng").asDouble());
        if (node.has("franchise_name")) {
            place.setFranchiseName(node.get("franchise_name").textValue());
        }
        addMenu(place);
        placeRepository.save(place);

    }

    private Address parseAddress(String str) {
        String[] piece = str.split(" ");
        Address address = new Address();
        address.setSido(piece[0]);
        address.setSigungu(piece[1]);
        if (piece[2].endsWith("가") || piece[2].contains("로")) {
            address.setDoro(piece[2]);
            address.setDong(piece[2]);
        } else {
            address.setDong(piece[2]);
        }

        if (piece.length == 4) {
            address.setDetail(piece[3]);
        } else if (piece.length == 5) {
            address.setDetail(piece[3] + piece[4]);
        }
        return address;

    }

    private Place addMenu(Place place) throws IOException {
        String response = responseMenu(place.getId());
        parseAddMenu(response, place);

        return place;
    }

    @Transactional
    private Place parseAddMenu(String response, Place place) throws IOException {
        Menu menu;
        if (place.getFranchiseName() != null) {
            menu = findFranchiseMenu(place.getFranchiseName());
            if (menu != null) {
                place.setMenu(menu);
                return place;
            }
        }
        JsonNode root = objectMapper.readTree(response);
        MenuItem menuItem;
        menu = new Menu();
        menu = menuRepository.save(menu);
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        if (place.getFranchiseName() != null && !place.getFranchiseName().isEmpty()) {
            menu.setName(place.getFranchiseName());
        } else {
            menu.setName(place.getName());
        }

        for (JsonNode items : root) {
            innerloop:
            for (JsonNode item : items.get("items")) {
                if (menuItems.size() == 10) {
                    break;
                }
                List<MenuItem> checks = menuItemRepository.findByName(item.get("name").textValue());
                if (checks != null && checks.size() > 0) {
                    MenuItem check = checks.get(0);
                    if (check != null) {
                        menuItems.add(check);
                        continue;
                    }
                }

                menuItem = new MenuItem();
                menuItem.setMenu(menu);
                menuItem = menuItemRepository.save(menuItem);
                if (item.has("image")) {
                    Image image = new Image();
                    image.setLocation(infoDomain + item.get("image").textValue());
                    image = imageRepository.save(image);
                    menuItem.setImage(image);
                }
                menuItem.setName(item.get("name").textValue());
                menuItem.setPrice(item.get("price").asInt());
                menuItem.setMenuType(item.get("section").textValue());
                menuItemRepository.save(menuItem);
                menuItems.add(menuItem);
            }
        }
        menu.setItems(menuItems);
        menuRepository.save(menu);
        return place;
    }

    private Menu findFranchiseMenu(String franchiseName) {
        return menuRepository.findMenuByName(franchiseName);
    }

}
