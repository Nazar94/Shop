package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.GoodFilter;
import ua.entity.Good;
import ua.entity.User;

public interface UserService {

	void save(User user);

	Page <User>findAll(GoodFilter filter, Pageable pageable);

	User findOne(String email);

	void saveGood(int id);

	List<User> findAll();

	User finaByName(String name);

	void saveGood(Principal principal, int id);
	
//	Page<Good> findAll(GoodFilter filter, Pageable pageable);

//	void delete(int id);

//	int createNewUser();

//	void addToShoppingCart(int userId, int goodId);

	List<User> findBuy(Pageable pageable);
	
	User findUserBuy(Principal principal);

	void deleteGood(Principal principal, int id);

//	void deleteFromShoppingCart(int goodId);
	
///	Page<Good> deleteFromShoppingCart(int goodId);
//	void deleteFromShoppingCart(int userId, int goodId);
	
///	Page<Good> findById(Principal principal,int goodId, Pageable pageable);
///	

}
