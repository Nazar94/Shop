package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.BasicFilter;
import ua.entity.Country;
import ua.entity.Material;



public interface MaterialService {


	List<Material> findAll();

	void delete(int id);

	void save(Material form);

	Material findOne(int id);
	
	Material findOne(String material);
	
	Page<Material> findAll(BasicFilter filter, Pageable pageable);

}
