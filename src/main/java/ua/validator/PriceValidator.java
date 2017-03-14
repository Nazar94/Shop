package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Price;

import ua.service.PriceService;

public class PriceValidator implements Validator {

	
	private final PriceService priceService;

	public PriceValidator(PriceService priceService) {
		this.priceService = priceService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Price.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Price price = (Price)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Can`t be empty");
		if(priceService.findOne(price.getPrice())!=null){
			errors.rejectValue("Price", "", "Already exist");
		}
		
	}
	
	
	
}
