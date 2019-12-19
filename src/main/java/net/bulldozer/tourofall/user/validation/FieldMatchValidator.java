package net.bulldozer.tourofall.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import net.bulldozer.tourofall.user.validation.annotation.FieldMatch;
import net.bulldozer.tourofall.user.validation.util.ValidatorUtil;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	private String firstFieldName;
	private String secondFieldName;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		try {
			String password = (String) ValidatorUtil.getFieldValue(value, firstFieldName);
			String passwordVerification = (String) ValidatorUtil.getFieldValue(value, secondFieldName);
			
			if(password.length() < 8 || passwordVerification.length() <8)
				return true;
			
			if (passwordsAreNotEqual(password, passwordVerification)) {
				ValidatorUtil.addValidationError(firstFieldName, context);
				ValidatorUtil.addValidationError(secondFieldName, context);

				return false;
			}
		} catch (final Exception ignore) {
		}
		return true;
	}

	private boolean passwordsAreNotEqual(String password, String passwordVerification) {
		return !(password == null ? passwordVerification == null : password.equals(passwordVerification));
	}
}
