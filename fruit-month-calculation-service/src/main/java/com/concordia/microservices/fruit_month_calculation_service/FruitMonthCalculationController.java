package com.concordia.microservices.fruit_month_calculation_service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FruitMonthCalculationController {
    @Autowired
    private Environment environment;

    @GetMapping("/fruit-month-price-calculation/fruit/{fruit}/month/{month}/quantity/{quantity}")
    public FruitMonthCalculation  retrieveFMP(@PathVariable String fruit,@PathVariable String month,@PathVariable BigDecimal quantity)
    {
        FruitMonthCalculation fmc_obj = new FruitMonthCalculation(fruit,month,quantity);

        String port = environment.getProperty("local.server.port");
        fmc_obj.setEnvironment(port);
        return fmc_obj;
    }
} 