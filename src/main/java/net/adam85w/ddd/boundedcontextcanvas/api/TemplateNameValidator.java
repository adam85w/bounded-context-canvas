package net.adam85w.ddd.boundedcontextcanvas.api;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateContextCollection;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateType;

public class TemplateNameValidator implements ConstraintValidator<TemplateNameConstraint, String> {

    private final TemplateContextCollection templateContextCollection;

    private TemplateType templateType;

    TemplateNameValidator(TemplateContextCollection templateContextCollection) {
        this.templateContextCollection = templateContextCollection;
    }

    @Override
    public void initialize(TemplateNameConstraint constraintAnnotation) {
        this.templateType = constraintAnnotation.templateType();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return templateContextCollection.templateExists(name) && templateContextCollection.getTemplateContext(name).isSupported(templateType);
    }
}
