package ua.service.implementation;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.form.GoodFilter;
import ua.entity.Good;
import ua.entity.Role;
import ua.entity.ShopingCart;
import ua.entity.User;
import ua.repository.GoodRepository;
import ua.repository.UserRepository;
import ua.service.GoodService;
import ua.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GoodRepository goodRepository;
	
	@Autowired
	private GoodService goodService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
/*	@Autowired
	private ShopingCartRepository shopingCartRepository;
*/	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
	}
	
	@PostConstruct
	public void admin(){
		User user = userRepository.findByUsername("admin");
		if(user==null){
			user = new User();
			user.setEmail("");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			user.setUsername("admin");
			userRepository.save(user);
		}
	}
/*
	@Override
	public int createNewUser() {
		return userRepository.saveAndFlush(new User()).getId();
	}
/*	
	@Override
	@Transactional
	public void addToShoppingCart(int userId, int goodId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		if(cart==null){
			cart = shopingCartRepository.save(new ShopingCart());
			user.setShopingCart(cart);
		}
		Good good = goodRepository.findOne(goodId);
		cart.add(good);
	}
	
	*/
/*	@Override
	public Page<User> findAll(GoodFilter filter, Pageable pageable) {
		return userRepository.findAll(pageable);
	}
*/
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

///	public void setShopingCartRepository(ShopingCartRepository shopingCartRepository) {
//		this.shopingCartRepository = shopingCartRepository;
//	}

	
	public GoodRepository getGoodRepository() {
		return goodRepository;
	}

	public void setGoodRepository(GoodRepository goodRepository) {
		this.goodRepository = goodRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public BCryptPasswordEncoder getEncoder() {
		return encoder;
	}

//	public ShopingCartRepository getShopingCartRepository() {
//		return shopingCartRepository;
//	}

	@Override
	public User findOne(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User finaByName(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	@Transactional
	public void saveGood(Principal principal, int id) {
		User user=userRepository.findByUsername(principal.getName());
		Good good=goodRepository.findOne(id);
		user.setGood(good);
		userRepository.save(user);
	}


/*	@Override
	public List<User> findBuy(Pageable pageable) {
		return userRepository.findAllBuy(pageable);
	}
*/
	@Override
	public void saveGood(int id) {
//		return userRepository.saveGood();
	}

	@Override
	public Page<User> findAll(GoodFilter filter, Pageable pageable){
	return userRepository.findAll(pageable);
	}


/*	
	@Override
	public void delete(int id) {
		userRepository.delete(id);
		
	}
*/
	/*
	@Override
	public Page<Good> findById(Principal principal, int goodId, Pageable pageable) {
		User user=userRepository.findByUsername(principal.getName());
		Good good=goodRepository.findOne(goodId);
		user.setGood(good);
		goodRepository.delete(goodId);
		return goodRepository.findAll(pageable);
		
	}

*/
	@Override
	public User findUserBuy(Principal principal) {
		if(principal!=null){
		String name=principal.getName();		
		return userRepository.findAllgoods(name);}
		return new User();
	}


	@Override
	public List<User> findBuy(Pageable pageable) {
		return userRepository.findAllBuy(pageable);
	}

	@Override
	@Transactional
	public void deleteGood(Principal principal, int id) {
		User user = userRepository.findByUsername(principal.getName());
		Good good =goodService.findById(id);
		Iterator<Good> iter=user.getGoods().iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(good)) {
				iter.remove();
				break;
			}
			
		}
	}
		
	}

