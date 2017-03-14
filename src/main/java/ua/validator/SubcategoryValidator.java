package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Subcategory;
import ua.service.SubcategoryService;


public class SubcategoryValidator implements Validator{

	private final SubcategoryService subcategoryService;

	public SubcategoryValidator(SubcategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Subcategory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Subcategory subcategory = (Subcategory)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subcategory", "", "Can`t be empty");
		if(subcategoryService.findOne(subcategory.getSubcategory())!=null){
			errors.rejectValue("subcategory", "", "Already exist");
		}
		
	}
	
	
}
