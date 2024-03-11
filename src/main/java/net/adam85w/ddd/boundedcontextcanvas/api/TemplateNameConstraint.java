package net.adam85w.ddd.boundedcontextcanvas.api;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TemplateNameValidator.class)
public @interface TemplateNameConstraint {

    TemplateType templateType();
    String message() default "Template name is incorrect or does not support the {templateType} type.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
