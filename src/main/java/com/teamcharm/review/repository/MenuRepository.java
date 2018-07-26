/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.repository;

import com.teamcharm.review.model.Menu;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author b005
 */
public interface MenuRepository extends CrudRepository<Menu, Long>{
    Menu findMenuByName(String name);
    
}
