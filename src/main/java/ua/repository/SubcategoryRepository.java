package ua.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Subcategory;

public interface SubcategoryRepository  extends JpaRepository<Subcategory, Integer>, JpaSpecificationExecutor<Subcategory> {
	
	@Query("SELECT s FROM Subcategory s LEFT JOIN FETCH s.category WHERE s.id=:id")
	
	Subcategory findOne(@Param("id")int id);
	@Query("SELECT s FROM Subcategory s WHERE s.subcategory=?1")
	Subcategory findBySubcategory(String subcategory);
	
	@Query(value="SELECT s FROM Subcategory s LEFT JOIN FETCH s.category",
			countQuery="SELECT count(s.id) FROM Subcategory s")
	Page<Subcategory> findAll(Pageable pageable);
	
}
