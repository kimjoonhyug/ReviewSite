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
@DiscriminatorValue("menu_item")
public class MenuImage extends Image{
    
    
    @JsonIgnore
    @ManyToOne
    private MenuItem menuItem;
    
    public MenuImage(String location) {
        super(location);
    }

    public MenuImage() {
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    
    
    
}
