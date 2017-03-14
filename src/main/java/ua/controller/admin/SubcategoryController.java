package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.form.SubcategoryFilter;
import ua.editor.CategoryEditor;
import ua.entity.Category;
import ua.entity.Subcategory;
import ua.service.CategoryService;
import ua.service.SubcategoryService;
import ua.validator.SubcategoryValidator;

@Controller
@RequestMapping("/admin/subcategory")
@SessionAttributes(names="subcategories")
public class SubcategoryController {


		@Autowired
		private SubcategoryService subcategoryService;
		

		@Autowired
		private CategoryService categoryService;
		
		@ModelAttribute("filter")
		public SubcategoryFilter getFilter(){
			return new SubcategoryFilter();
		}
		
		
		@ModelAttribute("subcategory")
		public Subcategory getForm(){
			return new Subcategory();
		}
		
		@InitBinder("subcategory")
		protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.setValidator(new SubcategoryValidator(subcategoryService));
			
		}
		
		@RequestMapping
		public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SubcategoryFilter filter){
			model.addAttribute("page", subcategoryService.findAll(filter, pageable));
			model.addAttribute("categories", categoryService.findAll());
			return "admin-subcategory";
		}
		
		@RequestMapping("/delete/{id}")
		public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SubcategoryFilter filter){
			subcategoryService.delete(id);
			return "redirect:/admin/subcategory"+getParams(pageable, filter);
		}
		
		@RequestMapping("/update/{id}")
		public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SubcategoryFilter filter){
			model.addAttribute("subcategory", subcategoryService.findOne(id));
			model.addAttribute("page", subcategoryService.findAll(filter, pageable));
			model.addAttribute("categories", categoryService.findAll());
			show(model, pageable, filter);
			return "admin-subcategory";
		}

	@RequestMapping(method=POST)
		public String save(@ModelAttribute("subcategory")@Valid Subcategory form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SubcategoryFilter filter ){
			if(br.hasErrors()){
				model.addAttribute("categories", categoryService.findAll());
				model.addAttribute("page", subcategoryService.findAll(filter, pageable));
				return "admin-subcategory";
			}
			subcategoryService.save(form);
			status.setComplete();
			return "redirect:/admin/subcategory"+getParams(pageable, filter);
		}
	
	
	private String getParams(Pageable pageable, SubcategoryFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
			if(!filter.getSubcategory().isEmpty()){
				buffer.append("&subcategory=");
				buffer.append(filter.getSubcategory());
			}
			for(Integer id : filter.getCategoryIds()){
				buffer.append("&category=");
				buffer.append(id);
			
		}
		}
		return buffer.toString();
	
	}
	
}
/*	
		@RequestMapping(method=POST)
		public String save(@ModelAttribute("subcategory") Subcategory subcategory, SessionStatus status){
			subcategoryService.save(subcategory);
			status.setComplete();
			return "redirect:/admin/subcategory";
		}*/


	
	
	

