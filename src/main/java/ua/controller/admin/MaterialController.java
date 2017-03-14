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
import ua.entity.Material;
import ua.service.MaterialService;
import ua.validator.MaterialValidator;


@Controller
@RequestMapping("/admin/material")
@SessionAttributes(names="material")
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	
	
	@ModelAttribute("material")
	public Material getForm(){
		return new Material();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@InitBinder("material")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MaterialValidator(materialService));
	}
	
	@RequestMapping
	public String show(SessionStatus status, Model model,@PageableDefault Pageable pageable,@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", materialService.findAll(filter, pageable));
		return "admin-material";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		materialService.delete(id);
		return "redirect:/admin/material"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("material", materialService.findOne(id));
		model.addAttribute("page", materialService.findAll(filter, pageable));
		return "admin-material";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("material")@Valid Material form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter ){
		if(br.hasErrors()){
			model.addAttribute("page", materialService.findAll());
			return "admin-material";
		}
		materialService.save(form);
		status.setComplete();
		return "redirect:/admin/material"+getParams(pageable, filter);
	}
}
	

