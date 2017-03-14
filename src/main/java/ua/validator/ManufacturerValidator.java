package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Manufacturer;
import ua.service.ManufacturerService;


public class ManufacturerValidator implements Validator {

	private final ManufacturerService manufacturerService;

	public ManufacturerValidator(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Manufacturer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Manufacturer manufacturer = (Manufacturer)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer", "", "Can`t be empty");
		if(manufacturerService.findOne(manufacturer.getManufacturer())!=null){
			errors.rejectValue("manufacturer", "", "Already exist");
		}
		
	}
	
}
