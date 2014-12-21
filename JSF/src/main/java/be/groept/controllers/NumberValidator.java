package be.groept.controllers;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.math.NumberUtils;

import com.sun.faces.util.MessageFactory;

@FacesValidator("numberValidator")
public class NumberValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null && !NumberUtils.isDigits(value.toString())) {
			throw new ValidatorException(MessageFactory.getMessage("not.numerical", value.toString()));
		}
	}
}
