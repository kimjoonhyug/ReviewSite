/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author b003
 */
@Entity
public class Menu {
    
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(mappedBy = "menu")
    private List<MenuItem> items;
    
    private String name;

    public Menu(long id, List<MenuItem> list) {
        this.id = id;
        this.items = list;
    }
    
    public Menu(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
