/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamcharm.review.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author b003
 */
@Entity
public class Address {
    @Id
    @GeneratedValue
    private long id;
    private String sido;
    private String sigungu;
    private String dong;
    private String doro;
    private String detail;
    private String fullAddress;
    
    public Address(){}

    public Address(String sido, String sigungu, String doro, String detail) {
        this.sido = sido;
        this.sigungu = sigungu;
        this.doro = doro;
        this.detail = detail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getSigungu() {
        return sigungu;
    }

    public void setSigungu(String sigungu) {
        this.sigungu = sigungu;
    }

    public String getDoro() {
        return doro;
    }

    public void setDoro(String doro) {
        this.doro = doro;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }
    
    public String getFullAddress() {
        if(fullAddress == null)
            return makeFull();
        return fullAddress;
        
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    
    private String makeFull() {
        StringBuilder sb = new StringBuilder();
        sb.append(sido);
        sb.append(" ");
        sb.append(sigungu);
        sb.append(" ");
        sb.append(dong);
        sb.append(" ");
        sb.append(doro);
        sb.append(" ");
        sb.append(detail);
        sb.append(" ");
        return sb.toString();
    }
    
    
    
    
}
