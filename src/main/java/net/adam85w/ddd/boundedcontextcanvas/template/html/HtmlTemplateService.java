package net.adam85w.ddd.boundedcontextcanvas.template.html;

import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.Template;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateInputException;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
class HtmlTemplateService implements TemplateService<String> {

    private static final String SUFFIX = ".html";

    private final TemplateEngine templateEngine;

    private final ApplicationContext applicationContext;

    HtmlTemplateService(TemplateEngine templateEngine, ApplicationContext applicationContext) {
        this.templateEngine = templateEngine;
        this.applicationContext = applicationContext;
    }

    @Override
    public Template<String> generate(String name, BoundedContext boundedContext) {
        try {
            Context context = new Context();
            context.setLocale(Locale.ENGLISH);
            context.setVariable("timestamp", LocalDateTime.now());
            context.setVariable("applicationContext", applicationContext);
            context.setVariable("boundedContext", boundedContext);
            return new Template<String>(name, this.templateEngine.process(name + SUFFIX, context));
        } catch (TemplateInputException exception) {
            throw new InvalidTemplateNameException(name + SUFFIX, exception);
        }
    }
}
