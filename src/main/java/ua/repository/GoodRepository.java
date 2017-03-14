package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Good;

public interface GoodRepository  extends JpaRepository<Good, Integer>, JpaSpecificationExecutor<Good> {
	
/*	@Query("SELECT i FROM Good i LEFT JOIN FETCH i.subcategory LEFT JOIN FETCH i.country"
			+ "LEFT JOIN FETCH i.delivery LEFT JOIN FETCH i.manufacturer LEFT JOIN FETCH i.material LEFT JOIN FETCH i.price")*/
	
	@Query("SELECT g FROM Good g LEFT JOIN FETCH g.subcategory LEFT JOIN FETCH g.manufacturer LEFT JOIN FETCH g.country LEFT JOIN FETCH g.delivery LEFT JOIN FETCH g.material" )
	List<Good> findAll();

	@Query(value="SELECT g FROM Good g LEFT JOIN FETCH g.subcategory LEFT JOIN FETCH g.manufacturer LEFT JOIN FETCH g.country LEFT JOIN FETCH g.delivery LEFT JOIN FETCH g.material",
			countQuery="SELECT count(g.id) FROM Good g")
	Page<Good> findAll(Pageable pageable);
	

	@Query("SELECT g FROM Good g LEFT JOIN FETCH g.subcategory LEFT JOIN FETCH g.manufacturer LEFT JOIN FETCH g.country LEFT JOIN FETCH g.delivery LEFT JOIN FETCH g.material WHERE g.id=:id")
	Good findOne(@Param("id")int id);
	
	/////

	@Query("SELECT u.count FROM User u  WHERE u.id=?1")
	Integer findCount(int id);
	
	
	
}
