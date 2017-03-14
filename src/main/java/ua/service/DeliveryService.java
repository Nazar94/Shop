package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.BasicFilter;
import ua.entity.Delivery;

public interface DeliveryService {

	List<Delivery> findAll();

	void delete(int id);

	void save(Delivery form);

	Delivery findOne(int id);
	
	Delivery findOne(String delivery);
	
	Page<Delivery> findAll(BasicFilter filter, Pageable pageable);
}
