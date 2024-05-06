package com.chiubilly.springbootmall.model;

import java.util.Date;

public class ExchangeRate {

    private Integer id;
    private Date exchangeDate;
    private double usdToNtd;
    private double rmbToNtd;
    private double usdToRmb;
    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public double getUsdToNtd() {
        return usdToNtd;
    }

    public void setUsdToNtd(double usdToNtd) {
        this.usdToNtd = usdToNtd;
    }

    public double getRmbToNtd() {
        return rmbToNtd;
    }

    public void setRmbToNtd(double rmbToNtd) {
        this.rmbToNtd = rmbToNtd;
    }

    public double getUsdToRmb() {
        return usdToRmb;
    }

    public void setUsdToRmb(double usdToRmb) {
        this.usdToRmb = usdToRmb;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
