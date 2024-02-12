package net.adam85w.ddd.boundedcontextcanvas.template.html;

import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.Template;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
class HtmlTemplateService implements TemplateService<String> {

    private static final String SUFFIX = ".html";

    private final TemplateEngine templateEngine;

    HtmlTemplateService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public Template<String> generate(String name, BoundedContext boundedContext) {
        Context context = new Context();
        context.setLocale(Locale.ENGLISH);
        context.setVariable("timestamp", LocalDateTime.now());
        context.setVariable("boundedContext", boundedContext);
        return new Template<String>(name, this.templateEngine.process(name + SUFFIX, context));
    }
}
