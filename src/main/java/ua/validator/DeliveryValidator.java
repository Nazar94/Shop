package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



import ua.entity.Country;
import ua.entity.Delivery;
import ua.service.CountryService;
import ua.service.DeliveryService;

public class DeliveryValidator implements Validator {

	
	private final DeliveryService deliveryService;

	public DeliveryValidator(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Delivery.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Delivery delivery = (Delivery)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "delivery", "", "Can`t be empty");
		if(deliveryService.findOne(delivery.getDelivery())!=null){
			errors.rejectValue("delivery", "", "Already exist");
		}
		
	}

	
}
