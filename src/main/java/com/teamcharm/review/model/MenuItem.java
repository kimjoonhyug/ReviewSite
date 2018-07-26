/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author b003
 */
@Entity
public class MenuItem {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int price;
    private String menuType;
    
    
    
    @OneToOne
    private MenuImage image;
    
    @ManyToOne
    private Menu menu;

    public MenuItem(long id, String name, int price, MenuImage image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public MenuItem(){}
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(MenuImage image) {
        this.image = image;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    
    
    
}
