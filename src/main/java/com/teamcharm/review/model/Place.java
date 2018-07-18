/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author b006
 */
@Entity
public class Place {
    @Id
    @GeneratedValue
    private long id;
    private long phone;
    private String website;
    private String Address;
   
    public Place(long id, long phone, String website, String Address) {
        this.phone = phone;
        this.website = website;
        this.Address = Address;
        this.id = id;
    }

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Place(){}
    
    public enum Type {마트,편의점,백화점,음식점,카페,주유소}
   
    
}
