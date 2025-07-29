package com.concordia.microservices.fruit_month_service;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FruitMonthPrice {
    @Id
    private Long id;
    
    @Column(name = "\"month\"")
    private String month;
    
    @Column
    private String fruit;

    @Column
    private String environment;
    
    @Column
    private BigDecimal fmp;
    
    public FruitMonthPrice(){}

    public FruitMonthPrice(Long id, String fruit, String month, BigDecimal fmp)
    {
        this.id = id;
        this.month = month;
        this.fruit = fruit;
        this.fmp = fmp;
    }

    public Long getId(){
        return this.id; }

    public String getFruit(){
        return this.fruit; }

    public String getMonth(){
        return this.month; }

    public BigDecimal getFmp(){
        return this.fmp; }        

    public String getEnvironment(){
        return this.environment;}

    public void setId(Long id){
        this.id = id; }

    public void setFruit(String fruit){
        this.fruit = fruit; }

    public void setMonth(String month){
        this.month = month; }

    public void setFmp(BigDecimal fmp){
        this.fmp = fmp; }

    public void setEnvironment(String environment){
        this.environment = environment; }
} 