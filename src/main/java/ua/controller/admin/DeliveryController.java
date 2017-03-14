package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.utils.ParamBuilder.getParams;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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






import ua.dto.form.BasicFilter;
import ua.entity.Country;
import ua.entity.Delivery;
import ua.service.DeliveryService;
import ua.validator.DeliveryValidator;




@Controller
@RequestMapping("/admin/delivery")
@SessionAttributes(names="deliveries")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	
	@ModelAttribute("delivery")
	public Delivery getForm(){
		return new Delivery();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@InitBinder("delivery")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new DeliveryValidator(deliveryService));
	}
	
	
	@RequestMapping
	public String show(SessionStatus status, Model model,@PageableDefault Pageable pageable,@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", deliveryService.findAll(filter, pageable));
		return "admin-delivery";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		deliveryService.delete(id);
		return "redirect:/admin/delivery"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("delivery", deliveryService.findOne(id));
		model.addAttribute("page", deliveryService.findAll(filter, pageable));
		return "admin-delivery";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("delivery")@Valid Delivery form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter ){
		if(br.hasErrors()){
			model.addAttribute("page", deliveryService.findAll());
			return "admin-delivery";
		}
		deliveryService.save(form);
		status.setComplete();
		return "redirect:/admin/delivery"+getParams(pageable, filter);
	}
}
