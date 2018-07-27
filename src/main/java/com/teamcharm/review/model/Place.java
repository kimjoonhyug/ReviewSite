/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import static java.util.function.IntUnaryOperator.identity;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    private long id;
    private String name, franchiseName;
    private BigInteger phone;
    private String website;
    
    @OneToOne
    private Image logo;
    
    private double rating;
    private double lat, lng;
    
    @ManyToOne
    private Menu menu;
    
    @OneToOne
    private Address address;
    private Type type;
    private String hours;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    List<Image> images;
    
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    List<Review> reviews;
    
    public Place(long id, String name, BigInteger phone, String website) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.id = id;
    }
    
    public Place(){}

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
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

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public enum Type {
        
        한식,피자, 양식, 중식, 치킨,
        족발보쌈, 일식, 야식, 분식,프랜차이즈,디저트,일식돈까스,피자양식;
        
         private static final Map<String,Type> ENUM_MAP;
        
        static {
            ENUM_MAP = Stream.of(Type.values()).collect(Collectors.toMap(Type::toString, type -> type));
        }
        
        public static Type get(String type) {
            return ENUM_MAP.get(type);
        }
    }
   
    
}
