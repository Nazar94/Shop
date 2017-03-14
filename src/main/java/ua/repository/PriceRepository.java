package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {

	Price findByPrice(int price);
	
}
