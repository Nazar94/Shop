package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.BasicFilter;
import ua.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	void delete(int id);

	void save(Category form);

	Category findOne(int id);
	
	Category findOne(String category);
	
	Page<Category> findAll(BasicFilter filter, Pageable pageable);

}
