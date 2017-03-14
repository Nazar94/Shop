package ua.service;

import java.util.List;

import ua.entity.Price;



public interface PriceService {

	List<Price> findAll();

	void delete(int id);

	void save(Price form);

	Price findOne1(int id);
	
	Price findOne(int price);
	
}
