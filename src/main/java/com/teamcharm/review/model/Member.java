/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

/**
 *
 * @author b006
 */
public class Member {
    private String name;
    private String pass;
    private String email;
    private long id;

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

    public Member(String name, String pass, String email, long id) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.id = id;
    }
    
    public Member(){
    
    }
    
}
