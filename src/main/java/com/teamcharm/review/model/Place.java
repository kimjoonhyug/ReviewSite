/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author b006
 */
@Entity
public class Place {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private long phone;
    private String website;
    
    private double rating;
    
    @ManyToOne
    private Menu menu;
    
    @OneToOne
    private Address address;
    private Type type;
    private String hours;
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    List<PlaceImage> images;
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    List<Review> reviews;
    
    public Place(long id, String name, long phone, String website) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.id = id;
    }
    
    public Place(){}

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PlaceImage> getImages() {
        return images;
    }

    public void setImages(List<PlaceImage> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public void updateRating() {
        rating = reviews.stream().mapToDouble(review -> review.getRating()).average().orElse(0);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public enum Type {
        
        한식,피자, 양식, 중국식, 치킨,
        족발,보쌈, 일식, 야식, 분식,프랜차이즈,디저트
    }
   
    
}
