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
import ua.entity.Manufacturer;
import ua.service.CountryService;
import ua.service.ManufacturerService;
import ua.validator.CountryValidator;
import ua.validator.ManufacturerValidator;


@Controller
@RequestMapping("/admin/manufacturer")
@SessionAttributes(names="manufacturer")
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;
	
	
	@ModelAttribute("manufacturer")
	public Manufacturer getForm(){
		return new Manufacturer();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@InitBinder("manufacturer")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ManufacturerValidator(manufacturerService));
	}
	
	@RequestMapping
	public String show(SessionStatus status, Model model,@PageableDefault Pageable pageable,@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", manufacturerService.findAll(filter, pageable));
		return "admin-manufacturer";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		manufacturerService.delete(id);
		return "redirect:/admin/manufacturer"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("manufacturer", manufacturerService.findOne(id));
		model.addAttribute("page", manufacturerService.findAll(filter, pageable));
		return "admin-manufacturer";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("manufacturer")@Valid Manufacturer form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter ){
		if(br.hasErrors()){
			model.addAttribute("page", manufacturerService.findAll());
			return "admin-manufacturer";
		}
		manufacturerService.save(form);
		status.setComplete();
		return "redirect:/admin/manufacturer"+getParams(pageable, filter);
	}
}
	

