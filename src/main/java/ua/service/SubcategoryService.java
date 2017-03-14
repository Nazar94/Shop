package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.SubcategoryFilter;
import ua.entity.Subcategory;



public interface SubcategoryService {

	List<Subcategory> findAll();

	void delete(int id);

	void save(Subcategory form);

	Subcategory findOne(int id);

	Subcategory findOne(String subcategory);

	Page<Subcategory> findAll(SubcategoryFilter filter, Pageable pageable);




	
}
