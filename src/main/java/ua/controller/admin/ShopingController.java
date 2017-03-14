package ua.controller.admin;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.dto.form.GoodFilter;
import ua.dto.form.Quantity;
import ua.entity.Good;
import ua.entity.Price;
import ua.entity.User;
import ua.service.GoodService;
import ua.service.UserService;


@ControllerAdvice
public class ShopingController {
	
	@Autowired
	private GoodService goodService;
	
	@ModelAttribute("quantity")
	public Quantity getQuantity(@CookieValue(defaultValue="0", name="userId") int userId){
		int count = goodService.findCount(userId);
		return new Quantity(count);
	}
}