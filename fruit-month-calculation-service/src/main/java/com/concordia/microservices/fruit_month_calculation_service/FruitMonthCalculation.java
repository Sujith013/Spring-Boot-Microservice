package com.concordia.microservices.fruit_month_calculation_service;

import java.math.BigDecimal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class FruitMonthCalculation {
    private Long id;
    private String month, fruit, environment;
    private BigDecimal fmp, quantity, totalCalculatedAmount;
    
    public FruitMonthCalculation(){}

    public FruitMonthCalculation(String fruit, String month, BigDecimal quantity)
    {
        this.month = month;
        this.fruit = fruit;
        this.quantity = quantity;
        calculatePrice();
    }

    public void calculatePrice(){
        try {
            String fmpMicroserviceURL = "http://localhost:8000/fruit-month-price/fruit/"+this.fruit+"/month/"+this.month;

            URL url = new URL(fmpMicroserviceURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            System.out.println(jsonObject);

            setId(jsonObject.getLong("id"));
            setFmp(jsonObject.getBigDecimal("fmp"));
            setTotalCalculatedAmount(this.quantity.multiply(this.fmp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getId(){
        return this.id; }

    public String getFruit(){
        return this.fruit; }

    public String getMonth(){
        return this.month; }

    public BigDecimal getFmp(){
        return this.fmp; }        

    public BigDecimal getQuantity(){
        return this.quantity; }

    public BigDecimal getTotalCalculatedAmount(){
        return this.totalCalculatedAmount; }

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
    
    public void setQuantity(BigDecimal quantity){
        this.quantity = quantity; }

    public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount){
        this.totalCalculatedAmount = totalCalculatedAmount; }
} 