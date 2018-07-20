/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author b005
 */
@Entity
@DiscriminatorValue("review")
public class ReviewImage extends Image{
    
    @JsonIgnore
    @ManyToOne
    private Review review;

    public ReviewImage(String location) {
        super(location);
    }

    public ReviewImage() {
    }
    
    
    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
    
    
}
