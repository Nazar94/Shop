package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.form.BasicFilter;
import ua.entity.Manufacturer;



public interface ManufacturerService {

	List<Manufacturer> findAll();

	void delete(int id);

	void save(Manufacturer form);

	Manufacturer findOne(int id);
	
	Manufacturer findOne(String manufacturer);
	
	Page<Manufacturer> findAll(BasicFilter filter, Pageable pageable);
}
