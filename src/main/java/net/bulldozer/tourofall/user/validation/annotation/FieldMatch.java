package net.bulldozer.tourofall.user.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.bulldozer.tourofall.user.validation.FieldMatchValidator;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
	String message() default "{net.bulldozer.tourofall.user.validation.annotation.FieldMatch.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    

    String first();
    String second();
}
