/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import java.time.LocalDateTime;

/**
 *
 * @author b003
 */
public class Review {
    private long id;
    private String content;
    private int readcount;
    private Place place;
    private Member member;
    private int rating;
    private LocalDateTime date;

    public Review(long id, String content, int readcount, Place place, Member member, int rating, LocalDateTime date) {
        this.id = id;
        this.content = content;
        this.readcount = readcount;
        this.place = place;
        this.member = member;
        this.rating = rating;
        this.date = date;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    
}
