package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Price;
import ua.service.PriceService;
import ua.validator.PriceValidator;

@Controller
@RequestMapping("/admin/price")
@SessionAttributes(names="prices")
public class PriceController {

	@Autowired
	private PriceService priceService;
	
	@ModelAttribute("price")
	public Price getPrice() {
		return new Price() ;
	}
	
	@InitBinder("price")
protected void initBinder(WebDataBinder binder) {
	binder.setValidator(new PriceValidator(priceService));
}
	
	@RequestMapping
	public String show (Model model){
		model.addAttribute("prices",priceService.findAll());
		return "admin-price";
				
	}
	
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("price", priceService.findOne(id));
		model.addAttribute("prices", priceService.findAll());
		return "admin-price";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		priceService.delete(id);
		return "redirect:/admin/price";
	}
/*	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("price") Price price, SessionStatus status){
		priceService.save(price);
		status.setComplete();
		return "redirect:/admin/price";
	}
	
	*/
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("price") @Valid Price form, BindingResult br, SessionStatus status, Model model){
		if(br.hasErrors()){
			model.addAttribute("prices", priceService.findAll());
			return "admin-price";
		}
		priceService.save(form);
		status.setComplete();
		return "redirect:/admin/price";
	}
	
}


