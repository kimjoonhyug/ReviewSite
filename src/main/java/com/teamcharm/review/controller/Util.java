/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.controller;

import java.math.BigInteger;

/**
 *
 * @author b005
 */
public class Util {
    
    static String makePhone(String number) {
        if(number.length() < 8)
            return number;
        StringBuilder sb = new StringBuilder(number);
        sb.insert(number.length() -4, '-');
        sb.insert(number.length()-8, '-');
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(makePhone(new BigInteger("50344444444").toString()));
    }
    
}
