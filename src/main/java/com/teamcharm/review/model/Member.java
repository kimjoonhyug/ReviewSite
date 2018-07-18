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
public class Member {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String pass;
    private String email;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member(long id, String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.id = id;
    }
    
    public Member(){
    
    }
    
}
