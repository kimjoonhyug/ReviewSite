/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.service;

import com.teamcharm.review.model.Member;
import com.teamcharm.review.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author b005
 */
@Service
public class MemberService implements UserDetailsService {
    
    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        UserDetails user = memberRepository.findByUsername(string);
        if(user == null)
            throw new UsernameNotFoundException(string);
        return user;
    }
    
    public Member findByUsername(String name) {
        return memberRepository.findByUsername(name);
    }
    
    public Member saveMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }
    
}
