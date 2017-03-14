package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.Category;
import ua.service.CategoryService;

public class CategoryValidator implements Validator {
	
	private final CategoryService categoryService;

	public CategoryValidator(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Category category = (Category)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Can`t be empty");
		if(categoryService.findOne(category.getCategory())!=null){
			errors.rejectValue("category", "", "Already exist");
		}
		
	}

}