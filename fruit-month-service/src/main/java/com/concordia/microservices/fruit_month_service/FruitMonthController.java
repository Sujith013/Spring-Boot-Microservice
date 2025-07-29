package com.concordia.microservices.fruit_month_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FruitMonthController {
    @Autowired
    private Environment environment;

    @Autowired
    private FruitMonthRepository repository;

    @GetMapping("/fruit-month-price/fruit/{fruit}/month/{month}")
    public FruitMonthPrice  retrieveFMP(@PathVariable String fruit,@PathVariable String month)
    {
        FruitMonthPrice fmp_obj = repository.findByFruitAndMonth(fruit,month);

        if(fmp_obj == null){
            throw new RuntimeException("Fruit Month Price not found");
        }

        String port = environment.getProperty("local.server.port");
        fmp_obj.setEnvironment(port);
        return fmp_obj;
    }
} 