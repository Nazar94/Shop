package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.GoodFilter;
import ua.dto.form.GoodForm;
import ua.entity.Good;

public interface GoodService {

	GoodForm findOne(int id);
	
	List<Good> findAll();
	
	void save(GoodForm good);
	
	void save(Good goods);

	void delete(int id);

	Page<Good> findAll(GoodFilter filter, Pageable pageable);
	
	Good findById(int id);
	
	int findCount(int id);



}
