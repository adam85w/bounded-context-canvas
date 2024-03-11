package net.adam85w.ddd.boundedcontextcanvas.template.html;

import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;
import net.adam85w.ddd.boundedcontextcanvas.template.*;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateInputException;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
class HtmlTemplateService implements TemplateService<String> {

    private static final String PREFIX = "../canvas/templates/";

    private static final String SUFFIX = ".html";

    private final TemplateEngine templateEngine;

    private final ApplicationContext applicationContext;

    private final TemplateContextCollection templateContextCollection;

    HtmlTemplateService(TemplateEngine templateEngine, ApplicationContext applicationContext, TemplateContextCollection templateContextCollection) {
        this.templateEngine = templateEngine;
        this.applicationContext = applicationContext;
        this.templateContextCollection = templateContextCollection;
    }

    @Override
    public Template<String> generate(String name, BoundedContext boundedContext) {
        try {
            Context context = new Context();
            context.setLocale(Locale.ENGLISH);
            context.setVariable("timestamp", LocalDateTime.now());
            context.setVariable("applicationContext", applicationContext);
            context.setVariable("boundedContext", boundedContext);
            context.setVariable("canvasContext", templateContextCollection.getTemplateContext(name));
            return new Template<String>(name, this.templateEngine.process(PREFIX + applicationContext.brand() + "/" + name + SUFFIX, context));
        } catch (TemplateInputException exception) {
            throw new InvalidTemplateFileNameException(applicationContext.brand() + "/" + name + SUFFIX, exception);
        }
    }
}
