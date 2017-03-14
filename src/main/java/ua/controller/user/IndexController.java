package ua.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.utils.ParamBuilder.getParams;

import java.math.BigDecimal;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.form.BasicFilter;
import ua.dto.form.GoodFilter;
import ua.dto.form.GoodForm;
import ua.entity.Good;
import ua.entity.User;
import ua.service.CategoryService;
import ua.service.CountryService;
import ua.service.DeliveryService;
import ua.service.GoodService;
import ua.service.ManufacturerService;
import ua.service.MaterialService;
import ua.service.PriceService;
import ua.service.SubcategoryService;
import ua.service.UserService;
import ua.validator.UserValidator;
@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GoodService goodService;
	
	@InitBinder("user")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator(userService));
	}

	@RequestMapping("/basketUser")
	public String BasketUser(Model model, @PageableDefault Pageable pageable,Principal principal ){
		model.addAttribute("user", userService.findUserBuy(principal));
		BigDecimal price =new BigDecimal(0);
/*		for(Good good:userService.findUserBuy(principal).getGoods()){
			price=price.add(good.getPrice());
		}*/
		model.addAttribute(price);
		return "user-basketUser";
	}
	
	
	@RequestMapping("/good/{id}")
	public String showGood(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter){
		model.addAttribute("good",goodService.findById(id));
		return "user-good";
}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
	
	@ModelAttribute("filter")
	public GoodFilter getFilter(){
		return new GoodFilter();
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user-login";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "user-registration";
	}
	
	@RequestMapping("/buy/{id}")
	public String buy(@PathVariable int id, Principal principal){
		if (principal==null) {
			return "redirect:/registration";
		} else {
			userService.saveGood(principal, id);
			return "redirect:/";
		}
	
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		userService.deleteGood(principal, id);
		return "redirect:/";
	}
	
	@RequestMapping(value="/registration", method=POST)
	public String registration(@ModelAttribute User user){
		userService.save(user);
		return "redirect:/login";
	}
														
	@RequestMapping("/")
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter,Principal principal){
		model.addAttribute("page", goodService.findAll(filter,pageable));
		model.addAttribute("user", userService.findUserBuy(principal));
		return "user-index";
}
	
	
	@RequestMapping("/basket/deleteGood/{id}")
	public String deletebuy(@PathVariable int id, Principal principal){
		userService.deleteGood(principal, id);
		return "redirect:/basketUser";
	}
	
}
