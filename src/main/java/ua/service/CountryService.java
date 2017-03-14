package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.BasicFilter;
import ua.entity.Country;


public interface CountryService {

	List<Country> findAll();

	void delete(int id);

	void save(Country form);

	Country findOne(int id);
	
	Country findOne(String country);
	
	Page<Country> findAll(BasicFilter filter, Pageable pageable);
	
}
