package ua.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.User;
import ua.service.UserService;

public class UserValidator implements Validator {

	
	 private Pattern pattern;
	    private Matcher matcher;
	 
	    private static final String EMAIL_PATTERN =
	                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
	                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 
	    private final UserService userService;
		
		public UserValidator(UserService userService) {
			this.userService = userService;
			pattern = Pattern.compile(EMAIL_PATTERN);
		}
		
		@Override
		public boolean supports(Class<?> clazz) {
			
			return clazz.equals(User.class);
		}

		@Override
		public void validate(Object target, Errors errors) {
			User user=(User)target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Can`t be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can`t be empty");
			matcher = pattern.matcher(user.getEmail());
			if(matcher.matches()){
				
			}
			if(userService.findOne(user.getEmail())!=null){
				errors.rejectValue("email", "", "User with this email alredy exist");
			}
		}
	 
	    public boolean validate(final String hex) {
	        matcher = pattern.matcher(hex);
	 
	        return matcher.matches();
	    }


}
