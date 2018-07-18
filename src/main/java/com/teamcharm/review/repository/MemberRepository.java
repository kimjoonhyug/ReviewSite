/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.repository;

import com.teamcharm.review.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author b005
 */
@Repository
public interface MemberRepository  extends CrudRepository<Member, Long>{
    
}
