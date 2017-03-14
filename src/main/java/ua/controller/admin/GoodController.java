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

import ua.dto.form.GoodFilter;
import ua.dto.form.GoodForm;
//import ua.editor.CategoryEditor;
import ua.editor.CountryEditor;
import ua.editor.DeliveryEditor;
import ua.editor.ManufacturerEditor;
import ua.editor.MaterialEditor;
import ua.editor.SubcategoryEditor;
//import ua.entity.Category;
import ua.entity.Country;
import ua.entity.Delivery;
import ua.entity.Manufacturer;
import ua.entity.Material;
import ua.entity.Subcategory;
//import ua.service.CategoryService;
import ua.service.CountryService;
import ua.service.DeliveryService;
import ua.service.GoodService;
import ua.service.ManufacturerService;
import ua.service.MaterialService;
import ua.service.PriceService;
import ua.service.SubcategoryService;
import ua.service.UserService;
import ua.validator.GoodValidator;


@Controller
@RequestMapping("/admin/good")
@SessionAttributes(names="goods")
public class GoodController {

	//@Autowired
	//private CategoryService categoryService;
	
	@Autowired
	private GoodService goodService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("good")
	public GoodForm getForm(){
		return new GoodForm();
	}
	
	@ModelAttribute("filter")
	public GoodFilter getFilter(){
		return new GoodFilter();
	}
	
	
	@InitBinder("good")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Subcategory.class, new SubcategoryEditor(subcategoryService));
		//binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Material.class, new MaterialEditor(materialService));
		binder.registerCustomEditor(Manufacturer.class, new ManufacturerEditor(manufacturerService));
		binder.registerCustomEditor(Delivery.class, new DeliveryEditor(deliveryService));
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService)); 
		binder.setValidator(new GoodValidator());
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter){
		model.addAttribute("page", goodService.findAll(filter, pageable));
		model.addAttribute("goods", goodService.findAll());////
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("countries", countryService.findAll());
		//model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subcategories", subcategoryService.findAll());
		return "admin-good";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter){
		goodService.delete(id);
		return "redirect:/admin/good"+getParams(pageable, filter);
	}
	
	
	@RequestMapping("/basket")
	public String Basket(Model model, @PageableDefault Pageable pageable ){
		model.addAttribute("users", userService.findBuy(pageable));
		return "admin-basket";
	}
	
	/*
	@RequestMapping("/add/{id}")
	public String showAdd(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter){
		model.addAttribute("good", goodService.findOne(id));
		model.addAttribute("page", goodService.findAll(filter, pageable));
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		//model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("subcategories", subcategoryService.findAll());
		return "admin-good";
	}
/*	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("goods", goodService.findAll());
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("countries", countryService.findAll());
		//model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subcategories", subcategoryService.findAll());
		model.addAttribute("good", goodService.findOne(id));
		return "admin-good";
	}
	*/
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter){
		model.addAttribute("good", goodService.findOne(id));
		model.addAttribute("page", goodService.findAll(filter,pageable));
		model.addAttribute("materials", materialService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("deliveries", deliveryService.findAll());
		model.addAttribute("countries", countryService.findAll());
		//model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("subcategories", subcategoryService.findAll());
		return "admin-good";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("good")@Valid GoodForm good, BindingResult br, Model model, SessionStatus sessionStatus, @PageableDefault Pageable pageable, @ModelAttribute("filter") GoodFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", goodService.findAll(filter, pageable));
			model.addAttribute("deliveries", deliveryService.findAll());
			model.addAttribute("materials", materialService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("prices", priceService.findAll());
			model.addAttribute("subcategories", subcategoryService.findAll());
			//model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("countries", countryService.findAll());
			return "admin-good";
		}
		goodService.save(good);
		sessionStatus.setComplete();
		return "redirect:/admin/good"+getParams(pageable, filter);
	}

	
/*	@RequestMapping(method=POST)
	public String save(@ModelAttribute("good") Good good, SessionStatus status){
		goodService.save(good);
		status.setComplete();
		return "redirect:/admin/good";
	}
	
*/
	
	private String getParams(Pageable pageable, GoodFilter filter){
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
		}
		if(!filter.getSearch().isEmpty()){
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		if(!filter.getMaxPrice().isEmpty()){
			buffer.append("&maxPrice=");
			buffer.append(filter.getMaxPrice());
		}
		if(!filter.getMinPrice().isEmpty()){
			buffer.append("&minPrice=");
			buffer.append(filter.getMinPrice());
		}
		for(Integer id : filter.getManufacturerIds()){
			buffer.append("&manufacturerIds=");
			buffer.append(id);
		}
		for(Integer id : filter.getCountryIds()){
			buffer.append("&countryIds=");
			buffer.append(id);
		}
		for(Integer id : filter.getSubcategoryIds()){
			buffer.append("&subcategoryIds=");
			buffer.append(id);
		}
		for(Integer id : filter.getDeliveryIds()){
			buffer.append("&deliveryIds=");
			buffer.append(id);
		}
		for(Integer id : filter.getMaterialIds()){
			buffer.append("&materialIds=");
			buffer.append(id);
		}
/*		for(Integer id : filter.getCategoryIds()){
			buffer.append("&categoryIds=");
			buffer.append(id);
		}*/
		int i = 0;
		return buffer.toString();
	}
	
	
	//
}
