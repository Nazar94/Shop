package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.GoodForm;


public class GoodValidator implements Validator{

	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(GoodForm.class);
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		GoodForm good = (GoodForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "good", "", "Can`t be empty");
		if(!PATTERN.matcher(good.getPrice()).matches()){
			errors.rejectValue("price", "", "Wrong format, only 2 digits after separator");
		}
	}
	
}
