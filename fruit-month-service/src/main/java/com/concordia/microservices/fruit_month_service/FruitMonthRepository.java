package com.concordia.microservices.fruit_month_service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitMonthRepository extends JpaRepository<FruitMonthPrice,Long>{
    FruitMonthPrice findByFruitAndMonth(String fruit, String month);
}
