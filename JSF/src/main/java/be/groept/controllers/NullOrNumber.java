package be.groept.controllers;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

@ConstraintComposition(CompositionType.OR)
@Size(min = 0, max = 0)
@Pattern(regexp = "[1-9]\\d*")
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NullOrNumber {

	String message() default "Validation for an optional numeric field failed.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
