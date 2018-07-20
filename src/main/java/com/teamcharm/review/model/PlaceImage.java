/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author b005
 */
@Entity
@DiscriminatorValue("place")
public class PlaceImage extends Image {
    
    @JsonIgnore
    @ManyToOne
    private Place place;
    
    public PlaceImage(String location) {
        super(location);
    }

    public PlaceImage() {
    }
    
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
