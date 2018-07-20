/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author b003
 */
@Entity
public class Review {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    private int readcount;
    
    @ManyToOne
    private Place place;
    
    @ManyToOne
    private Member reviewer;
    
    private int rating;
    private LocalDateTime reviewDate;
    
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    List<ReviewImage> images;

    public Review(long id, String content, int readcount, Place place, Member member, int rating, LocalDateTime date) {
        this.id = id;
        this.content = content;
        this.readcount = readcount;
        this.place = place;
        this.reviewer = member;
        this.rating = rating;
        this.reviewDate = date;
    }
    
    public Review(){}
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Member getReviewer() {
        return reviewer;
    }

    public void setReviewer(Member reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public List<ReviewImage> getImages() {
        return images;
    }

    public void setImages(List<ReviewImage> images) {
        this.images = images;
    }
    
    
    
    
}
