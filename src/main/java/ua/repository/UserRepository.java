package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Good;
import ua.entity.User;



public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>  {

	@Query("SELECT u FROM User u WHERE u.username=:username")
	User findByUsername(@Param("username")String username);

	@Query(value="SELECT g FROM Good g LEFT JOIN FETCH g.subcategory LEFT JOIN FETCH g.manufacturer LEFT JOIN FETCH g.country LEFT JOIN FETCH g.delivery LEFT JOIN FETCH g.material",
			countQuery="SELECT count(g.id) FROM Good g")
	Page<User> findAll(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.email=:email")
	User findByEmail(@Param("email")String email);
	
//	@Query("UPDATE User u SET u.goods=:good")
//	void saveGood(@Param("good")Good good);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.goods Where u.goods is not empty")
	List<User> findAllBuy(Pageable pageable);
//	
	@Query("UPDATE User u SET u.goods=:good")
	void saveGood(@Param("good")Good good);

///	@Query("SELECT u FROM User u JOIN FETCH u.goods g LEFT JOIN FETCH g.name")	
//	List<User> findAllBuy(Pageable pageable);
//
	@Query(value="SELECT u FROM User u JOIN FETCH u.goods g  WHERE u.username=:good")
	User findAllgoods(@Param("good")String good);

	
	
}
