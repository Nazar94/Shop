package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Category;
import ua.entity.Country;


public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>  {

	Category findByCategory(String category);
	
}
